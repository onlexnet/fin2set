package dj.services.token;

import nordigen.SpectacularJWTObtain;

public interface TokenService {

    /**
     * @return Acces token and refresh token
     */
    SpectacularJWTObtain getTokens();

}
