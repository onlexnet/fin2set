import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { ApolloProvider } from '@apollo/client';
import { apolloClientFactory } from './api/gql';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { PlaidLoginView } from './components/plaid/PlaidLoginView';
import { App } from './App';
import { Auth0Provider } from '@auth0/auth0-react';
import config from './config.json';
import useAuth from './api/auth/useAuth';
import { Label } from '@fluentui/react-components';
import { UploadView } from './components/upload/PlaidLoginView';

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

const mainView = <WithApollo />;
const plaidView = <PlaidLoginView />;
const uploadView = <UploadView />

const router = createBrowserRouter([
  { path: "/", element: mainView },
  { path: "/view1", element: plaidView },
  { path: "/upload", element: uploadView },
  // profile https://auth0.com/docs/quickstart/spa/react/02-calling-an-api
]);

const routerProvider = <Auth0Provider domain={auth0Domain} clientId={auth0ClientId} authorizationParams={
  { redirect_uri, display: 'page' }}>
  <RouterProvider router={router} />
</Auth0Provider>;

ReactDOM
  .createRoot(document.getElementById('root') as HTMLElement)
  .render(routerProvider);



// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
