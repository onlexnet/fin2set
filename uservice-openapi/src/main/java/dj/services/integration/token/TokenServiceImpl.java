package dj.services.integration.token;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dj.models.dto.SpectacularJWTObtainDTO;
import dj.models.dto.SpectacularJWTObtainMapper;
import dj.models.dto.SpectacularJWTRefreshDTO;
import dj.models.dto.SpectacularJWTRefreshMapper;
import lombok.RequiredArgsConstructor;
import nordigen.JWTObtainPairRequest;
import nordigen.JWTRefreshRequest;
import nordigen.SpectacularJWTObtain;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${NORDIGEN_SECRET_KEY}")
    private String secretKey;

    @Value("${NORDIGEN_SECRET_ID}")
    private String secretId;

    private final TokenClient tokenClient;
    private final SpectacularJWTObtainMapper spectacularJWTObtainMapper;
    private final SpectacularJWTRefreshMapper spectacularJWTRefreshMapper;

    private String refreshToken;

    @Override
    public SpectacularJWTObtainDTO getTokens() {
        JWTObtainPairRequest jwtObtainPairRequest = new JWTObtainPairRequest()
                .secretId(secretId)
                .secretKey(secretKey);

        SpectacularJWTObtain tokens = tokenClient.createTokens(jwtObtainPairRequest);
        refreshToken = tokens.getRefresh();
        return spectacularJWTObtainMapper.toDTO(tokens);
    }

    @Override
    public SpectacularJWTRefreshDTO refreshAccessToken() {
        JWTRefreshRequest jwtRefreshRequest = new JWTRefreshRequest();
        jwtRefreshRequest.setRefresh(refreshToken);
        var response = tokenClient.refreshAccessToken(jwtRefreshRequest);
        return spectacularJWTRefreshMapper.toDTO(response);
    }

    @Override
    public String buildBearerAuthToken() {
        SpectacularJWTObtainDTO spectacularJWTObtain = getTokens();

        StringBuilder sb = new StringBuilder();
        sb.append("Bearer ");
        sb.append(spectacularJWTObtain.getAccess());
        return sb.toString();
    }

}
