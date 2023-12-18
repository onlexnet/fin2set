import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import { MainView } from './Main.View';
import { useParams } from 'react-router-dom';
import { ChatView } from '../chat/Chat.View';

interface ChatRouteParams {
}

export const ChatRoute: React.FC<ChatRouteParams> = props => {

    const params = useParams();

    return (<ChatView />)
}
