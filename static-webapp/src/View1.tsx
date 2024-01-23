import { useEffect, useState } from "react";
import { Protocol, addressProvider } from "./addressProvider";
import { AppClient, CreateLinkTokenService } from "./api/oas";
import { useAppState } from "./components/AppStateContext";
import useDataFetching from "./components/view1/useDataFetching";

interface View1Props {
}

export const View1: React.FC<View1Props> = (props: View1Props) => {
    const { state, dispatch } = useAppState();

    const { data, error, loading } = useDataFetching();

    if (!loading) {
      alert(data);
    }
      
    return (
        <>
            Hello!
            response: { JSON.stringify(data) }
            state: { JSON.stringify(state) }
            dispatch: { JSON.stringify(dispatch)}
        </>);
}
