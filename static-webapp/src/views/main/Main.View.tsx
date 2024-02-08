import { Stack } from "@fluentui/react";
import React from "react";
import { ChatView } from "../chat/Chat.View";
import { DynamicPanelView } from "../../components/dynamicPanel/DynamicPanelView";

interface MainViewProps {
}

export const MainView: React.FC<MainViewProps> = props => {
  return (
    <Stack key="stack1" horizontal styles={{ root: { background: 'green', height: "100vh" } }}>
      <Stack.Item id='chatFrame' grow styles={{ root: { background: 'red' } }}>
        <Stack horizontal horizontalAlign='center'>
          <Stack.Item>
            <ChatView />
          </Stack.Item>
          <Stack.Item>
            <DynamicPanelView />
          </Stack.Item>
        </Stack>
      </Stack.Item>
    </Stack>);

}