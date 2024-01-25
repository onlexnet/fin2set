import { PlaidLinkOnSuccess, usePlaidLink } from "react-plaid-link";
import { useAppState } from "../AppStateContext";
import { startLink } from "./plaid";
import useDataFetching from "../../api/useDataFetching";
import { useCallback, useState } from "react";
import React from "react";


interface View1Props {
}

export const View1: React.FC<View1Props> = (props: View1Props) => {
    const { state, dispatch } = useAppState();
    const { data, loading } = useDataFetching();
    const [token, setToken] = useState<string | null>(null);
    const onSuccess = useCallback<PlaidLinkOnSuccess>((publicToken, metadata) => {
        // send public_token to your server
        // https://plaid.com/docs/api/tokens/#token-exchange-flow
        console.log(publicToken, metadata);
    }, []);

    const { open, ready } = usePlaidLink({
        token,
        onSuccess,
        // onEvent
        // onExit
    });


    // get link_token from your server when component mounts
    React.useEffect(() => {
        const createLinkToken = async () => {
            const response = await fetch('/api/create_link_token', { method: 'POST' });
            const { link_token } = await response.json();
            setToken(link_token);
        };
        createLinkToken();
    }, []);

    if (open) {
        console.log("open");
    }

    if (ready) {
        console.log("ready");
    }

    if (data && !loading) {
        const link = data.value;
        startLink(link)
    }

    return (
        <>
            Hello!
            response: {JSON.stringify(data)}
            state: {JSON.stringify(state)}
            dispatch: {JSON.stringify(dispatch)}
        </>);
}
