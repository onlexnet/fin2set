package dj.services.token;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import nordigen.JWTObtainPairRequest;
import nordigen.SpectacularJWTObtain;

@Data
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${NORDIGEN_SECRET_KEY}")
    private String secretKey;

    @Value("${NORDIGEN_SECRETó_ID}")
    private String secretId;

    private final TokenClient tokenClient ;

    @Override
    public SpectacularJWTObtain getTokens() {
        JWTObtainPairRequest jwtObtainPairRequest = new JWTObtainPairRequest()
        .secretId(secretId)
        .secretKey(secretKey);
        return tokenClient.createTokens(jwtObtainPairRequest);
    }



}
