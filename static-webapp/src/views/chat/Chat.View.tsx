import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import { ChatContainer, MainContainer, Message, MessageInput, MessageList, MessageModel, TypingIndicator } from "@chatscope/chat-ui-kit-react";
import { useState } from "react";
import { Role, useNewMessageMutation } from "../../api/gql/graphql";
import { Stack } from '@fluentui/react';

interface ChatViewProps {
}

export const ChatView = (props: ChatViewProps) => {

    const firstMessage: MessageModel = {
        message: "Hello, I am ChatGPT",
        sender: "ChatGPT",
        direction: 1,
        position: 0
    };
    const [messages, setMessages] = useState([firstMessage]);

    const [newMessageMutation, { loading }] = useNewMessageMutation({
        onCompleted(data, clientOptions) {
            const text = data.newMessage.text;
            const message: MessageModel = { message: text, direction: 'incoming', sender: "chat", position: "last" }
            const newMessages = [...messages, message];
            setMessages(newMessages);
        },
    });

    const handleSend = (text: string) => {
        const message: MessageModel = { message: text, direction: 'outgoing', sender: "user", position: "last" }
        const newMessages = [...messages, message];
        setMessages(newMessages);

        const dtoMessages = newMessages.map((message, i) => ({ text: message.message || '', role: message.direction === 'incoming' ? Role.Assistant : Role.User }));
        newMessageMutation({
            variables: { messages: dtoMessages }
        })
    }

    const result = <Stack horizontal styles={{ root: { background: 'green', position: "relative", height: "100vh", width: "700px" } }}>
        <Stack.Item id='chatFrame' grow styles={{ root: { background: 'red' } }}>
            <MainContainer>
                <ChatContainer>
                    <MessageList
                        typingIndicator={loading ? <TypingIndicator content="ChatGPT is typing" /> : null}>
                        {messages.map((message, i) => {
                            return <Message key={i} model={message} />
                        })}
                    </MessageList>
                    <MessageInput placeholder='Put message here' onSend={handleSend} />
                </ChatContainer>
            </MainContainer>
        </Stack.Item>
    </Stack>;
    return result;
}
