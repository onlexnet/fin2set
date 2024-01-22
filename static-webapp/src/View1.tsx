import { Protocol, addressProvider } from "./addressProvider";
import { AppClient, CreateLinkTokenService } from "./api/oas";
import { useAppState } from "./components/AppStateContext";

interface View1Props {
}

export const View1: React.FC<View1Props> = (props: View1Props) => {
    const { state, dispatch } = useAppState();

    const httpUrl = `${addressProvider(Protocol.HTTPS).host}/v1`;

    const appClient = new AppClient({
        BASE: httpUrl
    });

    const a = appClient.createLinkToken.getApiCreateLinkToken().then(it => {
        alert(it);
    })

    return (
        <>
            Hello!
            state: { JSON.stringify(state) }
            dispatch: { JSON.stringify(dispatch)}
        </>);
}
