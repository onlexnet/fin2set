import { ApolloClient, ApolloLink, createHttpLink, DefaultOptions, InMemoryCache, split } from "@apollo/client";
import { GraphQLClient } from 'graphql-request';
import { addressProvider, Protocol } from "./addressProvider";
import { getMainDefinition } from "@apollo/client/utilities";
import { OperationDefinitionNode } from 'graphql';
import { GraphQLWsLink } from '@apollo/client/link/subscriptions';
import { ClientOptions, createClient } from 'graphql-ws';

const graphqlHttpUrl = `${addressProvider(Protocol.HTTPS).host}/graphql`;
const graphqlWsUrl = `${addressProvider(Protocol.WS).host}/graphql`;

export const apolloClientFactory = (jwtToken: string) => {
  // configuration below is focused on Authentication
  // https://www.apollographql.com/docs/react/networking/authentication/

  const httpLink = createHttpLink({
    uri: graphqlHttpUrl,
  });

  const middlewareAuthLink = new ApolloLink((operation, forward) => {
    operation.setContext({
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Methods": "OPTIONS, GET, POST, PUT, PATCH, DELETE",
        Authorization: `Bearer ${jwtToken}`,
      },
    });
    return forward(operation);
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

  // const wsLink = new GraphQLWsLink({
  //   uri: graphqlUrl,
  //   options: {
  //     reconnect: true, // Opcja reconnect dla subskrypcji
  //   },
  // });

  const options: ClientOptions = {
    url: graphqlWsUrl,
    
    connectionParams: () => {  
      return {
        authToken: 'your-auth-token',
      } // if you have an auth token  
    },  
    retryAttempts: 5, // This will enable auto-reconnect with 5 attempts.  
    shouldRetry: () => true, // as we have cloud application, backend should be available 24/7

    retryWait: (retries) => { 
      console.log(`Sparta. Retries: ${retries}`);
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

  const unifiedHttpLink = middlewareAuthLink.concat(httpLink)
  const splitLink = split(
    ({ query }) => {
      const { kind, operation } = getMainDefinition(query) as OperationDefinitionNode;
      // alert(`kind: ${kind}, operation: ${operation}`);
      return kind === 'OperationDefinition' && operation === 'subscription';
    },
    wsLink,
    unifiedHttpLink
  );

  return new ApolloClient({
    cache: new InMemoryCache({}),
    link: splitLink,
    connectToDevTools: true,
    defaultOptions
  });
};

export const graphQlClient = new GraphQLClient(graphqlHttpUrl);
export const apolloClient = apolloClientFactory("");
