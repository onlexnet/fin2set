import { useAuth0 } from "@auth0/auth0-react";
import { useEffect, useState } from "react";

const useAuth = () => {
    const [data, setData] = useState<string>();
    const [error, setError] = useState<Error>();
    const [loading, setLoading] = useState(true);
    const { isLoading, getIdTokenClaims } = useAuth0();

    useEffect(() => {
        if (isLoading) {
            return;
        }
        async function fetchData() {
            try {
                const idToken = await getIdTokenClaims();
                setData(idToken?.__raw);
            } catch (error) {
                setError(error as Error);
            } finally {
                setLoading(false);
            }
        }

        // call the function
        fetchData();

    }, [getIdTokenClaims, isLoading, data, error, loading]);

    return { data, error, loading };
}

export default useAuth;
