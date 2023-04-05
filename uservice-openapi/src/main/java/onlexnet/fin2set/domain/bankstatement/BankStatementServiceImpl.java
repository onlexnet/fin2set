package onlexnet.fin2set.domain.bankstatement;

import java.util.UUID;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.domain.models.AccountDTO;
import onlexnet.fin2set.domain.models.NordigenBankStatemant;
import onlexnet.fin2set.domain.models.OnlexBankStatemantMapper;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.accounts.AccountService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankStatementServiceImpl implements BankStatementService {

    private final AccountService accountService;
    private final OnlexBankStatemantMapper onlexBankStatemantMapper;
    
    @Override
    public BankStatement getBankStatement(UUID accountID) {

        AccountDTO account = accountService.getAccount(accountID);
        NordigenBankStatemant nordigenBankStatemant = accountService.getTransactions(accountID).getBody();

        return onlexBankStatemantMapper.toBankStatement(nordigenBankStatemant, account);
    }
    
}
