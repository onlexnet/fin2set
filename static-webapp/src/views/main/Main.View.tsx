import { Stack } from "@fluentui/react";
import React from "react";
import { ChatView } from "../chat/Chat.View";
import { View, useViewSubscription } from "../../api/gql/graphql";
import { useNavigate } from "react-router-dom";

interface MainViewProps {
}

export const MainView: React.FC<MainViewProps> = props => {
  const { data: currentView } = useViewSubscription({})
  const navigate = useNavigate();

  if (currentView?.view === View.View1) {
    navigate("/view1");
  }

  return (
    <Stack key="stack1" horizontal styles={{ root: { background: 'green', height: "100vh" } }}>
      <Stack.Item id='chatFrame' grow styles={{ root: { background: 'red' } }}>
        <Stack horizontal horizontalAlign='center'>
          <Stack.Item>
            <ChatView />
          </Stack.Item>
        </Stack>
      </Stack.Item>
    </Stack>);

}