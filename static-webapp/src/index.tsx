import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { App } from './App';
import { Auth0Provider } from '@auth0/auth0-react';
import config from './config.json';
import { UploadView } from './components/upload/UploadView';
import { withApollo } from './components/withApollo/withApollo';
import { withAuth } from './components/withAuth/withAuth';

const { auth0Domain, auth0ClientId } = config;
const redirect_uri = window.location.origin;


const MainViewWithApollo = withApollo(App)
const MainView = withAuth(MainViewWithApollo);
const uploadView = <UploadView />

const router = createBrowserRouter([
  { path: "/", element: <MainView /> },
  { path: "/view1", element: uploadView },
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
