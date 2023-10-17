import { FluentProvider, Label, webLightTheme } from '@fluentui/react-components';
import './App.css';
import { Stack } from '@fluentui/react';
import { ChatFrame } from './Chat';
import { Default as Example } from './example';
import { useState } from 'react';
import { useMyqueryQuery } from './api/generated/graphql';

function App() {
  const [hidden, setVisibleStack] = useState(false);

  const { data, loading, error } = useMyqueryQuery({
    variables: {
    },
  });

  if (loading) return (<Label>Loading ....</Label>);

  if (error) return (<Label>Error!</Label>);

  const view = <FluentProvider theme={webLightTheme}>
    <div className="App">
      <Stack horizontal styles={{ root: { height: "100%" } }}>
        <Stack.Item id='chatFrame' align='center'>
          {chatView()}
        </Stack.Item>
        <Stack.Item id='view1' align='center' hidden={hidden} styles={{ root: { width: "150%", padding: "10" } }}>
          <Example hide={() => {
            setVisibleStack(true);
          }} />
        </Stack.Item>
      </Stack>
    </div>
  </FluentProvider>;

  return view;
}

const chatView = () => <ChatFrame />

export default App;

