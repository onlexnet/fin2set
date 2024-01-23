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
import env from './env.json';

const { auth0Domain, auth0ClientId } = env;

const client = apolloClientFactory("ignored token");
const redirect_uri = window.location.origin;
const mainView =
  <Auth0Provider domain={auth0Domain} clientId={auth0ClientId} authorizationParams={{
    redirect_uri, 
  }} >

    <ApolloProvider client={client}>
      <App />
    </ApolloProvider>
  </Auth0Provider>;

const router = createBrowserRouter([
  { path: "/", element: mainView },
  { path: "/view1", element: <View1 /> },
]);

ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
).render(
  <RouterProvider router={router} />
);


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
