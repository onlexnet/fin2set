package dj.services.integration.institutions;

import java.util.List;

import dj.models.dto.IntegrationDTO;

public interface InstitutionsService {

    List<IntegrationDTO> getListInstitutions(String country);

    IntegrationDTO getInstitution(String institutionID);
    
}
