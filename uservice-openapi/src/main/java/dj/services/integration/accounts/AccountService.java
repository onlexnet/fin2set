package dj.services.integration.accounts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import dj.models.NordigenBankStatemant;
import nordigen.AccountV2;

public interface AccountService {

    ResponseEntity<AccountV2> getAccount(UUID accountNumberID);

    ResponseEntity<NordigenBankStatemant> getTransactions(UUID accountNumberID);

}
