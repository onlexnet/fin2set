package onlexnet.fin2set.nordigen.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.domain.models.BankMapper;
import onlexnet.fin2set.nordigen.token.TokenService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InstitutionsServiceImpl implements InstitutionsService {

    private final InstitutionsClient institutionsClient;
    private final TokenService tokenService;
    private final BankMapper BankMapper;
    
    @Override
    public List<Bank> getListInstitutions(String country) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = institutionsClient.getListInstitutions(accessToken, country);
        return BankMapper.toDTO(response);
    }

    @Override
    public Bank getInstitution(String institutionID) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = institutionsClient.getInstitution(accessToken, institutionID);
        return BankMapper.toDTO(response);
    }        
    

}
