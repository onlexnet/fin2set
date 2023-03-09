package dj.services.accounts;

import dj.models.BankStatemantv2;
import nordigen.AccountV2;

public interface AccountService {

    AccountV2 getAccount(String accountID);

    BankStatemantv2 getTransactions(String accountID);

}
