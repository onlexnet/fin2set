package dj.services.integration.accounts;

import dj.models.NordigenBankStatemant;
import nordigen.AccountV2;

public interface AccountService {

    AccountV2 getAccount(String accountID);

    NordigenBankStatemant getTransactions(String accountNumberID);

}
