package onlexnet.fin2set.nordigen.integration.token;

import onlexnet.fin2set.nordigen.generated.SpectacularJWTObtain;
import onlexnet.fin2set.nordigen.generated.SpectacularJWTRefresh;

public interface TokenService {

    /**
     * @return Access token and refresh token
     */
    SpectacularJWTObtain getTokens();

    /**
     * @return new access token
     */
    SpectacularJWTRefresh refreshAccessToken();

    /**
     * Get new token and return Bearer access token
     */
    String buildBearerAuthToken();

}
