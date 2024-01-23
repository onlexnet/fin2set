import { useEffect, useState } from 'react';
import { Protocol, addressProvider } from '../../addressProvider';
import { AppClient, plaidToken } from '../../api/oas';

const httpUrl = `${addressProvider(Protocol.HTTPS).host}/v1`;

const appClient = new AppClient({
    BASE: httpUrl
});

const createLinkToken = appClient.createLinkToken;

function useDataFetching() {
  const [data, setData] = useState<plaidToken>();
  const [error, setError] = useState<Error>();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await createLinkToken.getApiCreateLinkToken();
        setData(response);
      } catch (error) {
        setError(error as Error);
      } finally {
        setLoading(false);
      }
    }

    fetchData();
  }, []);

  return { data, error, loading };
}

export default useDataFetching;
