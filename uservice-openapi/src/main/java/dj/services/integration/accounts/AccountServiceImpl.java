package dj.services.integration.accounts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dj.models.NordigenBankStatemant;
import dj.services.integration.token.TokenService;
import lombok.AllArgsConstructor;
import nordigen.Account;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final TokenService tokenService;
    private final AccountClient accountClient;

    @Override
    public ResponseEntity<Account> getAccount(UUID accountNumberID) {
        String accessToken = tokenService.buildBearerAuthToken();
        return accountClient.getAccount(accessToken, accountNumberID);
    }

    @Override
    public ResponseEntity<NordigenBankStatemant> getTransactions(UUID accountNumberID) {
        String accessToken = tokenService.buildBearerAuthToken();
        return accountClient.getTransactions(accessToken, accountNumberID);
    }


    
}
