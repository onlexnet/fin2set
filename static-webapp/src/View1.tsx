import { useAppState } from "./components/AppStateContext";
import useDataFetching from "./components/view1/useDataFetching";

interface View1Props {
}

export const View1: React.FC<View1Props> = (props: View1Props) => {
    const { state, dispatch } = useAppState();

    const { data } = useDataFetching();

    return (
        <>
            Hello!
            response: {JSON.stringify(data)}
            state: {JSON.stringify(state)}
            dispatch: {JSON.stringify(dispatch)}
        </>);
}
