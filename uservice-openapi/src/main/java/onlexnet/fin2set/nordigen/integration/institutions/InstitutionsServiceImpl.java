package onlexnet.fin2set.nordigen.integration.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.nordigen.generated.Integration;
import onlexnet.fin2set.nordigen.integration.token.TokenService;

@RequiredArgsConstructor
@Service
public class InstitutionsServiceImpl implements InstitutionsService {

    private final InstitutionsClient integrationClient ;
    private final TokenService tokenService;
    
    @Override
    public List<Integration> getListBanks(String country) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = integrationClient.getListIntegration(accessToken, country);
        return response.getBody();
    }

    @Override
    public Integration getBank(String bankID) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = integrationClient.getIntegration(accessToken, bankID);
        return response.getBody();
    }        
    
}
