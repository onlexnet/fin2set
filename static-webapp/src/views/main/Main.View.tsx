import { Stack } from "@fluentui/react";
import React from "react";
import { ChatView } from "../chat/Chat.View";
import { useViewSubscription } from "../../api/generated/graphql";

interface MainViewProps {
}

export const MainView: React.FC<MainViewProps> = props => {
  const { data: currentView } = useViewSubscription({})

  return (
    <Stack horizontal styles={{ root: { background: 'green' } }}>
      <Stack.Item id='chatFrame' grow styles={{ root: { background: 'red' } }}>
        <Stack horizontal horizontalAlign='center'>
          <Stack.Item>
            <ChatView />
          </Stack.Item>
        </Stack>
      </Stack.Item>
      <Stack.Item id='view1' styles={{ root: { background: 'yellow' } }} hidden={false}>
        current view name:{currentView?.view}
      </Stack.Item>
    </Stack>);

}