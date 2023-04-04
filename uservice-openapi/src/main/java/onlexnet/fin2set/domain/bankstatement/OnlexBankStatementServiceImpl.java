package onlexnet.fin2set.domain.bankstatement;

import java.util.UUID;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.api.dto.AccountDTO;
import onlexnet.fin2set.domain.OnlexBankStatement;
import onlexnet.fin2set.domain.models.NordigenBankStatemant;
import onlexnet.fin2set.domain.models.OnlexBankStatemantMapper;
import onlexnet.fin2set.nordigen.accounts.AccountService;
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
