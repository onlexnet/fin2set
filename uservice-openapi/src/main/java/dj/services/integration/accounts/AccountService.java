package dj.services.integration.accounts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import dj.models.NordigenBankStatemant;
import dj.models.dto.AccountDTO;

public interface AccountService {

    AccountDTO getAccount(UUID accountNumberID);

    ResponseEntity<NordigenBankStatemant> getTransactions(UUID accountNumberID);

}
