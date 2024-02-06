import { ISpinnerProps, Spinner, SpinnerSize } from "@fluentui/react";
import { Label } from "@fluentui/react-components";

const Loading: React.FC<Partial<ISpinnerProps>> = props => {
    return (
        <div>
            <Label>Connecting with server (Apollo protocol)</Label>
            <Spinner size={SpinnerSize.large} />
        </div>);
}

export default Loading;