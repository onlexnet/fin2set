package onlexnet.fin2set.nordigen.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.api.dto.IntegrationDTO;
import onlexnet.fin2set.api.dto.IntegrationMapper;
import onlexnet.fin2set.nordigen.token.TokenService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InstitutionsServiceImpl implements InstitutionsService {

    private final InstitutionsClient institutionsClient;
    private final TokenService tokenService;
    private final IntegrationMapper integrationMapper;
    
    @Override
    public List<IntegrationDTO> getListInstitutions(String country) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = institutionsClient.getListInstitutions(accessToken, country);
        return integrationMapper.toDTO(response);
    }

    @Override
    public IntegrationDTO getInstitution(String institutionID) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = institutionsClient.getInstitution(accessToken, institutionID);
        return integrationMapper.toDTO(response);
    }        
    

}
