package dj.services.integration.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import dj.models.dto.IntegrationDTO;
import dj.models.dto.IntegrationMapper;
import dj.services.integration.token.TokenService;
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
