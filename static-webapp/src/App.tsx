import { FluentProvider, webLightTheme } from '@fluentui/react-components';
import './App.css';
import { Stack } from '@fluentui/react';
import { ChatFrame } from './Chat';
import { Default as Example } from './example';
import { useState } from 'react';

function App() {
  const [hidden, setVisibleStack] = useState(false);

  return (
    <FluentProvider theme={webLightTheme}>
      <div className="App">

        <Stack horizontal styles={{ root: { height: "100%" } }}>

          <Stack.Item id='chatFrame' align='center'>
            <ChatFrame />
          </Stack.Item>
          
          <Stack.Item id='view1' align='center' hidden={hidden} styles={{ root: { width: "150%", padding: "10" } }}>
            <Example hide={() => {
              setVisibleStack(true);
             }} />
          </Stack.Item>

        </Stack>


      </div>
    </FluentProvider>
  );
}

export default App;
