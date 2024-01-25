import { PlaidLinkOnSuccess, usePlaidLink } from "react-plaid-link";
import { useAppState } from "../AppStateContext";
import useLinkTokenFetch from "../../api/useLinkTokenFetch";
import { useCallback, useState } from "react";
import React from "react";
import { usePlaidLogin } from "./usePlaidLogin";


interface PlaidLoginViewProps {
}

export const PlaidLoginView: React.FC<PlaidLoginViewProps> = (props: PlaidLoginViewProps) => {
    const { state, dispatch } = useAppState();
    const { data, loading, error } = useLinkTokenFetch();
    const [token, setToken] = useState<string | null>(null);

    const onSuccess = useCallback<PlaidLinkOnSuccess>((publicToken, metadata) => {
        // send public_token to your server
        // https://plaid.com/docs/api/tokens/#token-exchange-flow
        console.log(publicToken, metadata);
    }, []);

    const { open, ready } = usePlaidLink({
        token: data?.linkToken ?? null,
        onSuccess,
        // onEvent
        // onExit
    });

    usePlaidLogin(data);

    return (
        <>
            <p> Hello! </p>
            <p>response: {JSON.stringify(data)}</p>
            <p>state: {JSON.stringify(state)}</p>
            <p>dispatch: {JSON.stringify(dispatch)}</p>
        </>);
}
