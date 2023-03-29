package dj.services.onlex.bankstatement;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dj.models.NordigenBankStatemant;
import dj.models.OnlexBankStatemantMapper;
import dj.models.OnlexBankStatement;
import dj.models.dto.AccountDTO;
import dj.services.integration.accounts.AccountService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OnlexBankStatementServiceImpl implements OnlexBankStatementService {

    private final AccountService accountService;
    private final OnlexBankStatemantMapper onlexBankStatemantMapper;
    
    @Override
    public OnlexBankStatement getOnlexBankStatement(UUID accountID) {

        AccountDTO account = accountService.getAccount(accountID);
        NordigenBankStatemant nordigenBankStatemant = accountService.getTransactions(accountID).getBody();

        return onlexBankStatemantMapper.toOnlexBankStatement(nordigenBankStatemant, account);
    }
    
}
