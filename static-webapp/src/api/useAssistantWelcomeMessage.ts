import { useEffect, useState } from 'react';
import { Protocol, addressProvider } from '../addressProvider';
import { AppClient, OpenAPI } from './oas';
import useAuth from './auth/useAuth';

const httpUrl = `${addressProvider(Protocol.HTTPS).host}/v1`;

const appClient = new AppClient({
  BASE: httpUrl
});

const apiOperation = appClient.chat;

const useAssistantWelcomeMessage = () => {
  const { result: idToken, loading: authLoading, error: authError } = useAuth();
  const [welcomeMessage, setWelcomeMessage] = useState<string>("");
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
        const { value } = await apiOperation.welcomeMessage();

        setWelcomeMessage(value);
      } catch (error) {
        setError(error as Error);
      } finally {
        setLoading(false);
      }
    }
    fetchData();

  }, [authLoading, authError, idToken]);

  console.log(`loading: ${loading}, error: ${error}, welcomeMessage: ${welcomeMessage}`)
  return { loading, error, welcomeMessage };
}

export default useAssistantWelcomeMessage;
