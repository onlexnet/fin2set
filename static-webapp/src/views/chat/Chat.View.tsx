import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import { ChatContainer, MainContainer, Message, MessageInput, MessageList, MessageModel, TypingIndicator } from "@chatscope/chat-ui-kit-react";
import { useState } from "react";
import { Role, useNewMessageMutation } from "../../api/gql/graphql";
import { Stack } from '@fluentui/react';
import useAssistantWelcomeMessage from '../../api/useAssistantWelcomeMessage';

interface ChatViewProps {
}

export const ChatView = (props: ChatViewProps) => {

    // TODO move initial message to application state as UI is ready when AI is ready and returns first message
    const welcomeMessage = useAssistantWelcomeMessage();

    const [messages, setMessages] = useState<MessageModel[]>([]);

    let messageContent = "...";
    if (welcomeMessage.loading) {
        console.log(111111111111);
        messageContent = "...";
    } else if (welcomeMessage.error) {
        console.log(222222222222222);
        messageContent = JSON.stringify(welcomeMessage.error);
    } else {
        console.log(3333333333333);
        messageContent = welcomeMessage.welcomeMessage;
    }

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

    const welcomeMessageModel: MessageModel = {
        message: messageContent, direction: 'incoming', sender: "chat", position: 'first'
    }
    const welcomeMessageContainer = <Message key={-1} model={welcomeMessageModel} />

    const realMessages = messages.map((message, i) => {
        return <Message key={i} model={message} />
    });

    const chatStarted = welcomeMessage.welcomeMessage ? true : false;
    const inputMsg = chatStarted ? "Put message here" : "Wait ....";
    const result = <Stack horizontal styles={{ root: { background: 'green', position: "relative", height: "100vh", width: "700px" } }}>
        <Stack.Item id='chatFrame' grow styles={{ root: { background: 'red' } }}>
            <MainContainer>
                <ChatContainer>
                    <MessageList
                        typingIndicator={loading ? <TypingIndicator content="ChatGPT is typing" /> : null}>
                        {welcomeMessageContainer}
                        {realMessages}
                    </MessageList>
                    <MessageInput placeholder={inputMsg} onSend={handleSend} disabled={!chatStarted} />
                </ChatContainer>
            </MainContainer>
        </Stack.Item>
    </Stack>;
    return result;
}
