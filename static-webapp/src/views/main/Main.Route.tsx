import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import { ChatView } from '../chat/Chat.View';

interface ChatRouteParams {
}

export const ChatRoute: React.FC<ChatRouteParams> = props => {

    return (<ChatView />)
}
