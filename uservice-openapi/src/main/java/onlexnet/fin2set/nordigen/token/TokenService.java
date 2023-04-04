package onlexnet.fin2set.nordigen.token;

import onlexnet.fin2set.api.dto.SpectacularJWTObtainDTO;
import onlexnet.fin2set.api.dto.SpectacularJWTRefreshDTO;

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
