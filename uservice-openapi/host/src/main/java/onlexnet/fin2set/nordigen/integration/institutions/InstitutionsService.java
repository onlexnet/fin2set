package onlexnet.fin2set.nordigen.integration.institutions;

import java.util.List;

import onlexnet.fin2set.nordigen.generated.Integration;

public interface InstitutionsService {

    List<Integration> getListBanks(String country);

    Integration getBank(String bankID);
    
}
