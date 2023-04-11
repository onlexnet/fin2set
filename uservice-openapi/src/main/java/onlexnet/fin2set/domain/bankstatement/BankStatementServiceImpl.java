package onlexnet.fin2set.domain.bankstatement;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.accounts.AccountService;

@Service
@AllArgsConstructor
public class BankStatementServiceImpl implements BankStatementService {

    private final AccountService accountService;
    
    @Override
    public BankStatement getBankStatement(UUID accountID) {
        return accountService.getTransactions(accountID);
    }
    
}
