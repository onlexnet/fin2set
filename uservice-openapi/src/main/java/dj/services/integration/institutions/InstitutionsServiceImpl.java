package dj.services.integration.institutions;

import java.util.List;

import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.Integration;

@RequiredArgsConstructor
@Service
public class InstitutionsServiceImpl implements InstitutionsService {

    private final InstitutionsClient institutionsClient;
    private final TokenService tokenService;
    
    @Override
    public List<Integration> getListInstitutions(String country) {
        String accessToken = tokenService.buildBearerAuthToken();
        return institutionsClient.getListInstitutions(accessToken, country);
    }

    @Override
    public Integration getInstitution(String institutionID) {
        String accessToken = tokenService.buildBearerAuthToken();
        return institutionsClient.getInstitution(accessToken, institutionID);
    }        
    

}
