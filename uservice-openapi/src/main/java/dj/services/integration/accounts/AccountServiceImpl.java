package dj.services.integration.accounts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dj.models.NordigenBankStatemant;
import dj.models.dto.AccountDTO;
import dj.models.dto.AccountMapper;
import dj.services.integration.token.TokenService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final TokenService tokenService;
    private final AccountClient accountClient;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO getAccount(UUID accountNumberID) {
        String accessToken = tokenService.buildBearerAuthToken();
        var responseNordigen = accountClient.getAccount(accessToken, accountNumberID);
        return accountMapper.toDTO(responseNordigen.getBody());
    }

    @Override
    public ResponseEntity<NordigenBankStatemant> getTransactions(UUID accountNumberID) {
        String accessToken = tokenService.buildBearerAuthToken();
        return accountClient.getTransactions(accessToken, accountNumberID);
    }


    
}
