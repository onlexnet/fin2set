import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import { ChatView } from './Chat.View';
import { useParams } from 'react-router-dom';

interface ChatRouteParams {
}

export const ChatRoute: React.FC<ChatRouteParams> = props => {

    const params = useParams();

    return (<ChatView />)
}
