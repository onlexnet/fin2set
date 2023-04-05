package onlexnet.fin2set.nordigen.institutions;

import java.util.List;

import onlexnet.fin2set.domain.models.Bank;

public interface InstitutionsService {

    List<Bank> getListInstitutions(String country);

    Bank getInstitution(String institutionID);
    
}
