package onlexnet.fin2set.nordigen.institutions;

import java.util.List;

import onlexnet.fin2set.api.dto.IntegrationDTO;

public interface InstitutionsService {

    List<IntegrationDTO> getListInstitutions(String country);

    IntegrationDTO getInstitution(String institutionID);
    
}
