import { useEffect, useState } from 'react';
import { Protocol, addressProvider } from '../addressProvider';
import { AccessToken, AppClient, OpenAPI, PublicToken} from './oas';
import useAuth from './auth/useAuth';

// TODO review why I need to add /v1 in the address
const httpUrl = `${addressProvider(Protocol.HTTPS).host}/v1`;

const appClient = new AppClient({
  BASE: httpUrl
});

const apiCall = appClient.exchangeLinkToken;

function useAccessTokenFetch(request: PublicToken) {
  const { data: idToken, loading: authLoading, error: authError } = useAuth();
  const [data, setData] = useState<AccessToken>();
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
        const response = await apiCall.exchangeLinkToken(request);
        fetchData();

        setData(response);
      } catch (error) {
        setError(error as Error);
      } finally {
        setLoading(false);
      }
    }
    fetchData();

  }, [authLoading, authError, idToken, request]);

  return { data, error, loading };
}

export default useAccessTokenFetch;
