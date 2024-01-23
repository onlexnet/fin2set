import { FluentProvider, Label, webLightTheme } from '@fluentui/react-components';
import './App.css';
import { useMyqueryQuery } from './api/gql/graphql';
import { MainView } from './views/main/Main.View';

import { useAuth0 } from '@auth0/auth0-react';
import React, { useEffect } from 'react';


export const App: React.FC<{}> = props => {
  
  const { loading, error: queryError } = useMyqueryQuery({
  });
  const { loginWithRedirect, isAuthenticated, isLoading, error } = useAuth0();

  useEffect(() => {
    if (isLoading) return;
    if (isAuthenticated) return;

    loginWithRedirect()
  }, [loginWithRedirect, isAuthenticated, isLoading]);
  
  if (error) {
    alert('Error:' + JSON.stringify(error));
    return (<Label>Loading ....</Label>);
  }

  if (loading) return (<Label>Loading ....</Label>);

  if (queryError) return (<Label>Error!</Label>);

  alert(`isLoading: ${isLoading}, isAuthenticated: ${isAuthenticated}`);

  if (isLoading) {
    return (<Label>Loading ....</Label>);
  }

  if (!isAuthenticated) {
    return (<Label>not authenticated ....</Label>);
  }


  const view =
      <FluentProvider theme={webLightTheme}>
        <div className="App">
          <MainView />
        </div>
      </FluentProvider>;

  return view;
}
