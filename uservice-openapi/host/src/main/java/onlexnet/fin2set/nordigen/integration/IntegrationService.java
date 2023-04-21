package onlexnet.fin2set.nordigen.integration;

import java.util.List;

import onlexnet.fin2set.domain.models.Bank;

public interface IntegrationService {

    List<Bank> getListBanks(String country);

    Bank getBank(String bankID);
    
}
