package dj.services.accounts;

import org.springframework.stereotype.Service;

import dj.models.BankStatemantv2;
import dj.services.token.TokenService;
import lombok.AllArgsConstructor;
import nordigen.AccountV2;
import nordigen.SpectacularJWTObtain;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final TokenService tokenService;
    private final AccountClient accountClient;

    @Override
    public AccountV2 getAccount(String accountID) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();    
        
        return accountClient.getAccount(accessToken, accountID);
    }

    @Override
    public BankStatemantv2 getTransactions(String accountID) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        return accountClient.getTransactions(accessToken, accountID);
    }


    
}
