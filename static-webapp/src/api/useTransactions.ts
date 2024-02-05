import { useEffect, useState } from 'react';
import { Protocol, addressProvider } from '../addressProvider';
import { AccessToken, AppClient, OpenAPI, TransactionDTO } from './oas';
import useAuth from './auth/useAuth';

const httpUrl = `${addressProvider(Protocol.HTTPS).host}/v1`;

const appClient = new AppClient({
  BASE: httpUrl
});

const createLinkToken = appClient.transactions;

const useTransactions = (maybeAccessToken?: AccessToken) => {
  const { result: idToken, loading: authLoading, error: authError } = useAuth();
  const [transactions, setData] = useState<TransactionDTO[]>();
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

    if (!maybeAccessToken) {
      return;
    }
    const accessToken = maybeAccessToken;

    async function fetchData() {
      try {
        // TODO change static assignment to invocation-related assignment
        OpenAPI.TOKEN = idToken;
        OpenAPI.BASE = httpUrl;
        const response = await createLinkToken.transactions(accessToken);

        setData(response);
      } catch (error) {
        setError(error as Error);
      } finally {
        setLoading(false);
      }
    }
    fetchData();

  }, [authLoading, authError, idToken, maybeAccessToken]);

  return { transactions, error, loading };
}

export default useTransactions;
