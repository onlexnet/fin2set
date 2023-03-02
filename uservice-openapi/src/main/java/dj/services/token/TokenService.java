package dj.services.token;

import dj.dto.integration.secrets.token.Tokens;

public interface TokenService {

    /**
     * @return Acces token and refresh token
     */
    Tokens getTokens();

}
