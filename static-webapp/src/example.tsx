import { Button, ButtonProps } from "@fluentui/react-components";
import { MouseEventHandler } from "react";

type ExampleProps = ButtonProps & {
  hide: () => void
}

export const Default = (props: ExampleProps) => {

  const onClick: MouseEventHandler<HTMLAnchorElement & HTMLButtonElement> = (ev) => {
    props.hide();
  };

  return (<Button {... props} onClick={onClick}>Example</Button>);
};
