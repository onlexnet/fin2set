import React, { ComponentType, useEffect } from 'react';
import useAuth from '../../api/auth/useAuth';
import Loading from './WithApollo.Loading';
import Error from './WithApollo.Error'
import { apolloClientFactory } from '../../api/gql';
import { ApolloProvider } from '@apollo/client';

interface WithApolloProps {
  unused: boolean;
}

export const withApollo = <P extends object>(WrappedComponent: ComponentType<P & WithApolloProps>) => {

  const WithLogger: React.FC<P> = props => {

    useEffect(() => {
      console.log('Komponent został zamontowany.');

      // Funkcja czyszcząca
      return () => {
        console.log('Komponent zostanie odmontowany.');
      };
    }, []);

    const { result, error, loading } = useAuth();

    if (loading) return <Loading />;
    if (error) return <Error error={error} />

    const client = apolloClientFactory(result ?? "should be a token ...");

    const additionalProps: WithApolloProps = {
      unused: true
    };

    return (
      <ApolloProvider client={client}>
        <WrappedComponent {...props} {...additionalProps} />
      </ApolloProvider>);
  };

  return WithLogger;
};
