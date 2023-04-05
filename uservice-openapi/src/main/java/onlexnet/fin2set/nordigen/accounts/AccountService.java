package onlexnet.fin2set.nordigen.accounts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import onlexnet.fin2set.api.dto.AccountDTO;
import onlexnet.fin2set.api.dto.NordigenBankStatemant;

public interface AccountService {

    AccountDTO getAccount(UUID accountNumberID);

    ResponseEntity<NordigenBankStatemant> getTransactions(UUID accountNumberID);

}
