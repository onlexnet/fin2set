package onlexnet.fin2set.nordigen.accounts;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onlexnet.fin2set.domain.models.Account;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.models.mapers.AccountMapper;
import onlexnet.fin2set.nordigen.models.mapers.BankStatemantMapper;
import onlexnet.fin2set.nordigen.token.TokenService;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final TokenService tokenService;
    private final AccountClient accountClient;

    @Override
    public Account getAccount(UUID accountNumberID) {
        var accessToken = tokenService.buildBearerAuthToken();
        var responseNordigen = accountClient.getAccount(accessToken, accountNumberID);
       return AccountMapper.fromDTO(responseNordigen.getBody());
    }

    @Override
    public BankStatement getTransactions(UUID accountNumberID) {
        var accessToken = tokenService.buildBearerAuthToken();
        var nordigenBankStatemant = accountClient.getTransactions(accessToken, accountNumberID).getBody();
        var account = getAccount(accountNumberID);
        return BankStatemantMapper.fromDTO(nordigenBankStatemant, account);
    }


    
}
