package dj.services.integration.token;

import nordigen.SpectacularJWTObtain;
import nordigen.SpectacularJWTRefresh;

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
