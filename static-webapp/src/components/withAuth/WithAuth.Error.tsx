import { Label } from "@fluentui/react-components";

interface ErrorProps {
    error: any
}

const Error: React.FC<ErrorProps> = props => {
    const { error } = props;
    return (<Label>Login Error!: { JSON.stringify(error)}</Label>);
}

export default Error;