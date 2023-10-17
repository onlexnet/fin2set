import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import { ChatContainer, MainContainer, Message, MessageInput, MessageList, MessageModel, TypingIndicator } from "@chatscope/chat-ui-kit-react";
import { useState } from "react";
import { useNewMessageMutation } from "./api/generated/graphql";

interface ChatScopeProps {

}

export const ChatScope = (props: ChatScopeProps) => {

    const firstMessage: MessageModel = {
        message: "Hello, I am ChatGPT",
        sender: "ChatGPT",
        direction: 1,
        position: 0
    };
    const [messages, setMessages] = useState([firstMessage]);

    const [newMessageMutation, { data, loading, error }] = useNewMessageMutation({
        onCompleted(data, clientOptions) {
            const text = data.newMessage.text;
            const message: MessageModel = { message: text, direction: 'incoming', sender: "chat", position: "last" }
            const newMessages = [...messages, message];
            setMessages(newMessages);
        },
    });

    const handleSend = (text: string) => {
        const message: MessageModel = { message: text, direction: "outgoing", sender: "user", position: "last" }
        const newMessages = [...messages, message];
        setMessages(newMessages);
        newMessageMutation({
            variables: { text: text }
        })
    }

    const result =
        <div style={{ position: "relative", height: "100vh", width: "700px" }}>
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
        </div>;
    return result;
}