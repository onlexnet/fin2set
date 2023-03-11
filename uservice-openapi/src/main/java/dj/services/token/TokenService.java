package dj.services.token;

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

}
