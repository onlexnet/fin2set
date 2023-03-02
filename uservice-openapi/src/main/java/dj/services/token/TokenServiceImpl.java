package dj.services.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import nordigen.JWTObtainPairRequest;
import nordigen.SpectacularJWTObtain;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${NORDIGEN_SECRET_KEY}")
    private String secretKey;

    @Value("${NORDIGEN_SECRET_ID}")
    private String secretId;

    @Autowired
    private TokenClient tokenClient ;

    @Override
    public SpectacularJWTObtain getTokens() {
        JWTObtainPairRequest jwtObtainPairRequest = new JWTObtainPairRequest()
        .secretId(secretId)
        .secretKey(secretKey);
        return tokenClient.createTokens(jwtObtainPairRequest);
    }



}
