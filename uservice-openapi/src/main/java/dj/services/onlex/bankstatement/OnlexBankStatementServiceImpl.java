package dj.services.onlex.bankstatement;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dj.models.NordigenBankStatemant;
import dj.models.OnlexBankStatemantMapper;
import dj.models.OnlexBankStatement;
import dj.services.integration.accounts.AccountService;
import lombok.AllArgsConstructor;
import nordigen.Account;

@Service
@AllArgsConstructor
public class OnlexBankStatementServiceImpl implements OnlexBankStatementService {

    private final AccountService accountService;
    private final OnlexBankStatemantMapper onlexBankStatemantMapper;
    
    @Override
    public OnlexBankStatement getOnlexBankStatement(UUID accountID) {

        Account account = accountService.getAccount(accountID).getBody();
        NordigenBankStatemant nordigenBankStatemant = accountService.getTransactions(accountID).getBody();

        return onlexBankStatemantMapper.toOnlexBankStatement(nordigenBankStatemant, account);
    }
    
}
