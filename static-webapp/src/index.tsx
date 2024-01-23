import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { ApolloProvider } from '@apollo/client';
import { apolloClientFactory } from './api';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { View1 } from './View1';

const client = apolloClientFactory("ignored token");
const mainView =
    <ApolloProvider client={client}>
      <App />
    </ApolloProvider>;

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
