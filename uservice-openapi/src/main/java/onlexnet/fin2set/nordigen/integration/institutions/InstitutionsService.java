package onlexnet.fin2set.nordigen.integration.institutions;

import java.util.List;

import onlexnet.fin2set.domain.models.Bank;

public interface InstitutionsService {

    List<Bank> getListBanks(String country);

    Bank getBank(String bankID);
    
}
