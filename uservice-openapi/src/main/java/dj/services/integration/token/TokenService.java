package dj.services.integration.token;

import dj.models.dto.SpectacularJWTObtainDTO;
import dj.models.dto.SpectacularJWTRefreshDTO;

public interface TokenService {

    /**
     * @return Access token and refresh token
     */
    SpectacularJWTObtainDTO getTokens();

    /**
     * @return new access token
     */
    SpectacularJWTRefreshDTO refreshAccessToken();

    /**
     * Get new token and return Bearer access token
     */
    String buildBearerAuthToken();

}
