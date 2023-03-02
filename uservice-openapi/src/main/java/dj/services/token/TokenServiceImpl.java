package dj.services.token;

import org.springframework.beans.factory.annotation.Value;

import dj.dto.integration.secrets.Secrets;
import dj.dto.integration.secrets.token.Tokens;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${NORDIGEN_SECRET_KEY}")
    private String secretKey;

    @Value("${NORDIGEN_SECRETó_ID}")
    private String secretId;

    private final TokenClient tokenClient ;

    @Override
    public Tokens getTokens() {
        Secrets secrets = new Secrets()
                .setSecretKey(secretKey)
                .setSecretId(secretId);
        return tokenClient.createTokens(secrets);
    }



}
