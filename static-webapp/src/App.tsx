import { FluentProvider, Label, webLightTheme } from '@fluentui/react-components';
import './App.css';
import { Stack } from '@fluentui/react';
import { useMyqueryQuery, useViewSubscription } from './api/generated/graphql';
import { MainView } from './views/main/Main.View';

function App() {
  const { loading, error } = useMyqueryQuery({
  });

  if (loading) return (<Label>Loading ....</Label>);

  if (error) return (<Label>Error!</Label>);

  const view = <FluentProvider theme={webLightTheme}>
    <div className="App">
      <MainView />
    </div>
  </FluentProvider>;

  return view;
}

export default App;

