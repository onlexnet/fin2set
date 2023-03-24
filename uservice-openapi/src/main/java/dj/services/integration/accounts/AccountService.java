package dj.services.integration.accounts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import dj.models.NordigenBankStatemant;
import nordigen.Account;

public interface AccountService {

    ResponseEntity<Account> getAccount(UUID accountNumberID);

    ResponseEntity<NordigenBankStatemant> getTransactions(UUID accountNumberID);

}
