package onlexnet.fin2set.nordigen.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.nordigen.generated.JWTObtainPairRequest;
import onlexnet.fin2set.nordigen.generated.JWTRefreshRequest;
import onlexnet.fin2set.nordigen.generated.SpectacularJWTObtain;
import onlexnet.fin2set.nordigen.generated.SpectacularJWTRefresh;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${NORDIGEN_SECRET_KEY}")
    private String secretKey;

    @Value("${NORDIGEN_SECRET_ID}")
    private String secretId;

    private final TokenClient tokenClient;

    private String refreshToken;

    @Override
    public SpectacularJWTObtain getTokens() {
        JWTObtainPairRequest jwtObtainPairRequest = new JWTObtainPairRequest()
                .secretId(secretId)
                .secretKey(secretKey);

        var tokens = tokenClient.createTokens(jwtObtainPairRequest).getBody();
        refreshToken = tokens.getRefresh();
        return tokens;
    }

    @Override
    public SpectacularJWTRefresh refreshAccessToken() {
        JWTRefreshRequest jwtRefreshRequest = new JWTRefreshRequest();
        jwtRefreshRequest.setRefresh(refreshToken);
        return tokenClient.refreshAccessToken(jwtRefreshRequest).getBody();
    }

    @Override
    public String buildBearerAuthToken() {
        SpectacularJWTObtain spectacularJWTObtain = getTokens();

        StringBuilder sb = new StringBuilder();
        sb.append("Bearer ");
        sb.append(spectacularJWTObtain.getAccess());
        return sb.toString();
    }

}
