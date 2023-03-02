package dj.services.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import dj.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.Integration;
import nordigen.SpectacularJWTObtain;

@RequiredArgsConstructor
@Service
public class InstitutionsServiceImpl implements InstitututionsService {

    private final InstitutionsClient institutionsClient;
    private final TokenService tokenService;
    
    public List<Integration> getListBanks(String country) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();
        return institutionsClient.getBankList(accessToken, country);
    }

}
