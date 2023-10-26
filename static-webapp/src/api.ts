import { ApolloClient, ApolloLink, createHttpLink, DefaultOptions, InMemoryCache, split } from "@apollo/client";
import { GraphQLClient } from 'graphql-request';
import { addressProvider, Protocol } from "./addressProvider";
import { WebSocketLink } from '@apollo/client/link/ws';
import { getMainDefinition } from "@apollo/client/utilities";
import { OperationDefinitionNode } from 'graphql';
import { GraphQLWsLink } from '@apollo/client/link/subscriptions';
import { createClient } from 'graphql-ws';

const graphqlHttpUrl = `${addressProvider(Protocol.HTTP).host}/graphql`;
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

  const client = createClient({ url: graphqlWsUrl });
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
