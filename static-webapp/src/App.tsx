import { FluentProvider, Label, webLightTheme } from '@fluentui/react-components';
import './App.css';
import { Stack } from '@fluentui/react';
import { useCounterSubscription, useMyqueryQuery } from './api/generated/graphql';
import { ChatScope } from './ChatScope';

function App() {
  const { loading, error } = useMyqueryQuery({
  });

  const { data: data1, loading: loading1 } = useCounterSubscription({});

  if (loading) return (<Label>Loading ....</Label>);

  if (error) return (<Label>Error!</Label>);

  const view = <FluentProvider theme={webLightTheme}>
    <div className="App">
      <Stack horizontal styles={{ root: { background: 'green' } }}>
        <Stack.Item id='chatFrame' grow styles={{ root: { background: 'red' } }}>
          <Stack horizontal horizontalAlign='center'>
            <Stack.Item>
              <ChatScope />
            </Stack.Item>
          </Stack>
        </Stack.Item>
        <Stack.Item id='view1' styles={{ root: { background: 'yellow' } }} hidden={false}>
          ticks: {data1?.ticks}
          <p></p>
          loading:{loading1}
        </Stack.Item>
      </Stack>
    </div>
  </FluentProvider>;

  return view;
}

export default App;

