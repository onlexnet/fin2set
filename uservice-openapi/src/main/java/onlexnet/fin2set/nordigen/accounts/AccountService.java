package onlexnet.fin2set.nordigen.accounts;

import java.util.UUID;

import onlexnet.fin2set.domain.models.Account;
import onlexnet.fin2set.domain.models.BankStatement;

public interface AccountService {

    Account getAccount(UUID accountNumberID);

    BankStatement getTransactions(UUID accountNumberID);

}
