package dj.services.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import dj.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.Integration;

@RequiredArgsConstructor
@Service
public class InstitutionsServiceImpl implements InstitutionsService {

    private final InstitutionsClient institutionsClient;
    private final TokenService tokenService;
    
    @Override
    public List<Integration> getListBanks(String country) {
        var tokens = tokenService.getTokens();
        var accessToken = "Bearer " + tokens.getAccess();
        return institutionsClient.getBankList(accessToken, country);
    }        
    

}
