package onlexnet.fin2set.nordigen.integration.accounts;

import java.util.UUID;

import onlexnet.fin2set.nordigen.generated.Account;
import onlexnet.fin2set.nordigen.models.NordigenBankStatemant;

public interface AccountService {

    Account getAccount(UUID accountNumberID);

    NordigenBankStatemant getTransactions(UUID accountNumberID);

}
