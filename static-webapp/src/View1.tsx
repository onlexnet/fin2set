import { useAppState } from "./components/AppStateContext";

interface View1Props {
}

export const View1: React.FC<View1Props> = (props: View1Props) => {
    const { state, dispatch } = useAppState();

    return (
        <>
            Hello!
            state: { JSON.stringify(state) }
            dispatch: { JSON.stringify(dispatch)}
        </>);
}
