import { FluentProvider, Label, webLightTheme } from '@fluentui/react-components';
import './App.css';
import { useMyqueryQuery } from './api/gql/graphql';
import { MainView } from './views/main/Main.View';

import React from 'react';
import { Spinner, SpinnerSize } from '@fluentui/react';

interface AppProps {
  unused: boolean
}

export const App: React.FC<AppProps> = props => {

  const { loading, error: queryError } = useMyqueryQuery({
  });

  if (loading) return (<Spinner size={SpinnerSize.large} />);

  if (queryError) return (<Label>Query Error! {JSON.stringify(queryError)}</Label>);

  const view =
    <FluentProvider theme={webLightTheme}>
      <div className="App">
        <MainView />
      </div>
    </FluentProvider>;

  return view;
}
