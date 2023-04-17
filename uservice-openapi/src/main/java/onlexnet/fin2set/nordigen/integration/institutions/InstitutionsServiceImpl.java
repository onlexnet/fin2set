package onlexnet.fin2set.nordigen.integration.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.nordigen.integration.token.TokenService;
import onlexnet.fin2set.nordigen.models.mappers.BankMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InstitutionsServiceImpl implements InstitutionsService {

    private final InstitutionsClient integrationClient ;
    private final TokenService tokenService;
    
    @Override
    public List<Bank> getListBanks(String country) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = integrationClient.getListIntegration(accessToken, country);
        return BankMapper.fromDTO(response.getBody());
    }

    @Override
    public Bank getBank(String bankID) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = integrationClient.getIntegration(accessToken, bankID);
        return BankMapper.fromDTO(response.getBody());
    }        
    
}
