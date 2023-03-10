package dj.services.onlex;

import org.springframework.stereotype.Service;

import dj.models.NordigenBankStatemant;
import dj.models.OnlexBankStatemantMapper;
import dj.models.OnlexBankStatement;
import dj.services.accounts.AccountService;
import lombok.AllArgsConstructor;
import nordigen.AccountV2;

@Service
@AllArgsConstructor
public class OnlexServiceImpl implements OnlexService {

    private final AccountService accountService;
    private final OnlexBankStatemantMapper onlexBankStatemantMapper;
    
    @Override
    public OnlexBankStatement getOnlxBankStatement(String accountID) {

        AccountV2 account = accountService.getAccount(accountID);
        NordigenBankStatemant nordigenBankStatemant = accountService.getTransactions(accountID);

        return onlexBankStatemantMapper.toOnlexBankStatement(nordigenBankStatemant, account);
    }
    
}
