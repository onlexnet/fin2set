package onlexnet.fin2set.nordigen.accounts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import onlexnet.fin2set.domain.models.AccountDTO;
import onlexnet.fin2set.domain.models.NordigenBankStatemant;

public interface AccountService {

    AccountDTO getAccount(UUID accountNumberID);

    ResponseEntity<NordigenBankStatemant> getTransactions(UUID accountNumberID);

}
