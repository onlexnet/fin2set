import { Stack } from "@fluentui/react";
import React from "react";
import { ChatView } from "../chat/Chat.View";
import { View, useViewSubscription } from "../../api/gql/graphql";
import { useNavigate } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";

interface MainViewProps {
}

export const MainView: React.FC<MainViewProps> = props => {
  const { user } = useAuth0();
  const { data: currentView } = useViewSubscription({})
  const navigate = useNavigate();

  if (currentView?.view === View.View1) {
    navigate("/view1");
  }

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
        <Stack.Item>
          current view name:{currentView?.view} (v1)
        </Stack.Item>
        <Stack.Item>
          user: {JSON.stringify(user?.email)}
        </Stack.Item>
      </Stack.Item>
    </Stack>);

}