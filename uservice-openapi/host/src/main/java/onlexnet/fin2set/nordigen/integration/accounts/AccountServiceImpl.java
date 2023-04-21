package onlexnet.fin2set.nordigen.integration.accounts;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onlexnet.fin2set.nordigen.generated.Account;
import onlexnet.fin2set.nordigen.integration.token.TokenService;
import onlexnet.fin2set.nordigen.models.NordigenBankStatemant;

@Service
@AllArgsConstructor
class AccountServiceImpl implements AccountService{

    private final TokenService tokenService;
    private final AccountClient accountClient;

    @Override
    public Account getAccount(UUID accountNumberID) {
        var accessToken = tokenService.buildBearerAuthToken();
        var responseNordigen = accountClient.getAccount(accessToken, accountNumberID);
       return responseNordigen.getBody();
    }

    @Override
    public NordigenBankStatemant getTransactions(UUID accountNumberID) {
        var accessToken = tokenService.buildBearerAuthToken();
        return accountClient.getTransactions(accessToken, accountNumberID).getBody();
    }


    
}
