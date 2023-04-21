package onlexnet.fin2set.nordigen.integration;

import java.util.List;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.nordigen.mappers.BankMapper;
import onlexnet.fin2set.nordigen.token.TokenService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IntegrationServiceImpl implements IntegrationService {

    private final IntegrationClient integrationClient ;
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
