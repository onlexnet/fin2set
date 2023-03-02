package dj.services.token;

import org.springframework.stereotype.Service;

import nordigen.SpectacularJWTObtain;

public interface TokenService {

    /**
     * @return Acces token and refresh token
     */
    SpectacularJWTObtain getTokens();

}
