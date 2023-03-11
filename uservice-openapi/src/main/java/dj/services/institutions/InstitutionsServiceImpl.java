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
    public List<Integration> getListInstitutions(String country) {
        var accessToken = "Bearer " + tokenService.getTokens().getAccess();
        return institutionsClient.getListInstitutions(accessToken, country);
    }

    @Override
    public Integration getInstitution(String institutionID) {
        var accessToken = "Bearer " + tokenService.getTokens().getAccess();
        return institutionsClient.getInstitution(accessToken, institutionID);
    }        
    

}
