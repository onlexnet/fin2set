import { useAuth0 } from "@auth0/auth0-react";
import { useEffect, useState } from "react";
import config from '../../config.json';

function useAuth() {
    const [data, setData] = useState<string>();
    const [error, setError] = useState<Error>();
    const [loading, setLoading] = useState(true);
    const { getAccessTokenSilently } = useAuth0();
    const domain = config.auth0Domain;
    useEffect(() => {
        async function fetchData() {
            try {
                const accessToken = await getAccessTokenSilently({
                    authorizationParams: {
                        audience: `https://${domain}/api/v2/`,
                        scope: "read:current_user",
                    },
                });
                setData(accessToken);
            } catch (error) {
                setError(error as Error);
            } finally {
                setLoading(false);
            }
        }

        // call the function
        fetchData()
            // make sure to catch any error
            .catch(console.error);

    }, [getAccessTokenSilently]);

    return { data, error, loading };
}

export default useAuth;
