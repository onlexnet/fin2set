import React, { ComponentType, useEffect } from 'react';
import { useAuth0 } from '@auth0/auth0-react';
import { Label, Spinner, SpinnerSize } from '@fluentui/react';

interface WithAuthProps {
}

export const withAuth = <P extends object>(WrappedComponent: ComponentType<P & WithAuthProps>) => {

  const WithAuth: React.FC<P> = props => {

    const { loginWithRedirect, isAuthenticated, isLoading, error } = useAuth0();

    useEffect(() => {
      if (isLoading) return;
      if (isAuthenticated) return;

      loginWithRedirect()
    }, [loginWithRedirect, isAuthenticated, isLoading]);

    if (error) {
      return (<Label>'Error:' + JSON.stringify(error)</Label>);
    }

    if (isLoading) return (<Spinner size={SpinnerSize.large} />);

    if (isLoading) {
      return (<Spinner size={SpinnerSize.large} />);
    }

    if (!isAuthenticated) {
      return (<Label>not authenticated ....</Label>);
    }

    return (
      <WrappedComponent {...props} />);
  }

  return WithAuth;
};
