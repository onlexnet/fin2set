package dj.services.onlex.bankstatement;

import org.springframework.stereotype.Service;

import dj.models.NordigenBankStatemant;
import dj.models.OnlexBankStatemantMapper;
import dj.models.OnlexBankStatement;
import dj.services.integration.accounts.AccountService;
import lombok.AllArgsConstructor;
import nordigen.AccountV2;

@Service
@AllArgsConstructor
public class OnlexBankStatementServiceImpl implements OnlexBankStatementService {

    private final AccountService accountService;
    private final OnlexBankStatemantMapper onlexBankStatemantMapper;
    
    @Override
    public OnlexBankStatement getOnlexBankStatement(String accountID) {

        AccountV2 account = accountService.getAccount(accountID);
        NordigenBankStatemant nordigenBankStatemant = accountService.getTransactions(accountID);

        return onlexBankStatemantMapper.toOnlexBankStatement(nordigenBankStatemant, account);
    }
    
}
