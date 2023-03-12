package dj.services.integration.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import nordigen.JWTObtainPairRequest;
import nordigen.JWTRefreshRequest;
import nordigen.SpectacularJWTObtain;
import nordigen.SpectacularJWTRefresh;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${NORDIGEN_SECRET_KEY}")
    private String secretKey;

    @Value("${NORDIGEN_SECRET_ID}")
    private String secretId;

    @Autowired
    private TokenClient tokenClient ;

    private String refreshToken;

    @Override
    public SpectacularJWTObtain getTokens() {
        JWTObtainPairRequest jwtObtainPairRequest = new JWTObtainPairRequest()
        .secretId(secretId)
        .secretKey(secretKey);
        SpectacularJWTObtain tokens = tokenClient.createTokens(jwtObtainPairRequest);
        refreshToken = tokens.getRefresh();
        return tokens;
    }

    @Override
    public SpectacularJWTRefresh refreshAccessToken() {
        JWTRefreshRequest jwtRefreshRequest = new JWTRefreshRequest();
        jwtRefreshRequest.setRefresh(refreshToken);
        return tokenClient.refreshAccessToken(jwtRefreshRequest);
    }



}
