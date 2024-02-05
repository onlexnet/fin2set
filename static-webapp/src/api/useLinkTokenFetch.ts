import { useEffect, useState } from 'react';
import { Protocol, addressProvider } from '../addressProvider';
import { AppClient, LinkToken, OpenAPI } from './oas';
import useAuth from './auth/useAuth';

const httpUrl = `${addressProvider(Protocol.HTTPS).host}/v1`;

const appClient = new AppClient({
  BASE: httpUrl
});

const createLinkToken = appClient.createLinkToken;

const useLinkTokenFetch = () => {
  const { data: idToken, loading: authLoading, error: authError } = useAuth();
  const [data, setData] = useState<LinkToken>();
  const [error, setError] = useState<Error>();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (authLoading) {
      setLoading(true);
      return;
    }

    if (authError) {
      setError(authError);
      return;
    }

    async function fetchData() {
      try {
        // TODO change static assignment to invocation-related assignment
        OpenAPI.TOKEN = idToken;
        OpenAPI.BASE = httpUrl;
        const response = await createLinkToken.createLinkToken();

        setData(response);
      } catch (error) {
        setError(error as Error);
      } finally {
        setLoading(false);
      }
    }
    fetchData();

  }, [authLoading, authError, idToken]);

  return { data, error, loading };
}

export default useLinkTokenFetch;