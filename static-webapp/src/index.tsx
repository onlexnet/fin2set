import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { ApolloProvider } from '@apollo/client';
import { apolloClientFactory } from './api';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { View1 } from './components/view1/View1';
import { App } from './App';
import { Auth0Provider } from '@auth0/auth0-react';
import config from './config.json';
import useAuth from './api/auth/useAuth';
import { Label } from '@fluentui/react-components';

const { auth0Domain, auth0ClientId } = config;
const redirect_uri = window.location.origin;

const WithApollo: React.FC<{}> = props => {
  const { data: token, error, loading } = useAuth();

  if (loading) return (<Label>Loading ....</Label>);
  if (error) return (<Label>Login Error!: { JSON.stringify(error)}</Label>);

  const client = apolloClientFactory(token ?? "should be a token ...");
  return (
    <ApolloProvider client={client}>
      <App />
    </ApolloProvider>
  );
}

const mainView =
  <Auth0Provider domain={auth0Domain} clientId={auth0ClientId} authorizationParams={
    { redirect_uri, display: 'page' }}>
    <WithApollo />
  </Auth0Provider>;

const router = createBrowserRouter([
  { path: "/", element: mainView },
  { path: "/view1", element: <View1 /> },
  // profile https://auth0.com/docs/quickstart/spa/react/02-calling-an-api
]);

ReactDOM
  .createRoot(document.getElementById('root') as HTMLElement)
  .render(<RouterProvider router={router} />);



// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
