import { ApolloClient, ApolloLink, createHttpLink, DefaultOptions, from, InMemoryCache, split } from "@apollo/client";
import { GraphQLClient } from 'graphql-request';
import { addressProvider, Protocol } from "../addressProvider";
import { getMainDefinition } from "@apollo/client/utilities";
import { GraphQLWsLink } from '@apollo/client/link/subscriptions';
import { ClientOptions, createClient } from 'graphql-ws';

const graphqlHttpsUrl = `${addressProvider(Protocol.HTTPS).host}/graphql`;
const graphqlWssUrl = `${addressProvider(Protocol.WSS).host}/graphql`;

export const apolloClientFactory = (idToken: string) => {

  const BEARER_TOKEN = `Bearer ${idToken}`;

  // configuration below is focused on Authentication
  // https://www.apollographql.com/docs/react/networking/authentication/

  const authLink = new ApolloLink((operation, forward) => {
    operation.setContext({
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Methods": "OPTIONS, GET, POST, PUT, PATCH, DELETE",
        Authorization: BEARER_TOKEN,
      },
    });
    return forward(operation);
  });

  const httpLink = createHttpLink({
    uri: graphqlHttpsUrl,
  });

  const defaultOptions: DefaultOptions = {
    watchQuery: {
      fetchPolicy: 'no-cache',
      errorPolicy: 'ignore',
    },
    query: {
      fetchPolicy: 'network-only',
      errorPolicy: 'all',
    },
  };

  const options: ClientOptions = {
    url: graphqlWssUrl,
    connectionParams: {
      token: BEARER_TOKEN,
      Authorization: BEARER_TOKEN,
      headers: {
        token: BEARER_TOKEN,
        Authorization: BEARER_TOKEN,
      }
    },
    retryAttempts: 3, // This will enable auto-reconnect with 5 attempts.  
    shouldRetry: () => true, // as we have cloud application, backend should be available 24/7

    retryWait: (retries) => {
      // return immediatelly so that reconnection may started asap
      // for some reason, it does not reconnect immediately (checked by stopping and starting server)
      return Promise.resolve();
    },
    // retryWait: 3 * 1000, // Retry every 3 seconds  
    on: {
      opened: () => console.log('Connection opened!'),
      closed: () => console.log('Connection closed!'),
      error: (error) => console.log('Error:', error),
      connected: (socket) => console.log('Connected!'),
      connecting: () => console.log('Connecting ...'),
    },
  };

  const client = createClient(options);
  const wsLink = new GraphQLWsLink(client);

  const splitLink = split(
    ({ query }) => {
      const definition = getMainDefinition(query);
      return (
        definition.kind === 'OperationDefinition' &&
        definition.operation === 'subscription'
      );
    },
    wsLink,
    httpLink
  );

  const link = from([authLink, splitLink]);

  return new ApolloClient({
    cache: new InMemoryCache({}),
    link: link,
    connectToDevTools: true,
    defaultOptions,
    credentials: 'include'
  });
};

export const graphQlClient = new GraphQLClient(graphqlHttpsUrl);
export const apolloClient = apolloClientFactory("");
