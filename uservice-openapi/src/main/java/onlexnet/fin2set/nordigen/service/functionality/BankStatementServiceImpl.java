package onlexnet.fin2set.nordigen.service.functionality;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.integration.accounts.AccountService;
import onlexnet.fin2set.nordigen.models.mappers.BankStatemantMapper;

@Service
@AllArgsConstructor
public class BankStatementServiceImpl implements BankStatementService {

    private final AccountService accountService;
    
    @Override
    public BankStatement getBankStatement(UUID accountID) {
      var nordigenBankStatemant = accountService.getTransactions(accountID);
      var account = accountService.getAccount(accountID);
        return BankStatemantMapper.fromDTO(nordigenBankStatemant, account);
    }
    
}
