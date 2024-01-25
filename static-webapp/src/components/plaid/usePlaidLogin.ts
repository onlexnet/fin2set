import { useEffect, useState } from "react";
import { LinkToken, PublicToken } from "../../api/oas";
import { startLink } from "./plaidLogin";

export const usePlaidLogin = (linkToken?: LinkToken) => {

  const [loading, setLoading] = useState(true);
  const [publicToken, setToken] = useState<PublicToken>();

  useEffect(() => {
    async function fetchData() {
      try {
        if (!linkToken) {
          return;
        }
        const publicLink = await startLink(linkToken.linkToken);
        setToken(publicLink);
      } catch (error) {
        console.error(error)
      } finally {
        setLoading(false);
      }
    }
    fetchData();


  }, [linkToken]);

  return { loading, publicToken };

};
