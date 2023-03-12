package dj.services.integration.accounts;

import org.springframework.stereotype.Service;

import dj.models.NordigenBankStatemant;
import dj.services.integration.token.TokenService;
import lombok.AllArgsConstructor;
import nordigen.AccountV2;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final TokenService tokenService;
    private final AccountClient accountClient;

    @Override
    public AccountV2 getAccount(String accountID) {
        String accessToken = tokenService.buildBearerAuthToken();

        return accountClient.getAccount(accessToken, accountID);
    }

    @Override
    public NordigenBankStatemant getTransactions(String accountID) {
        String accessToken = tokenService.buildBearerAuthToken();

        return accountClient.getTransactions(accessToken, accountID);
    }


    
}
