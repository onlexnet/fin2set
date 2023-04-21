package onlexnet.fin2set.domain.bankstatement;

import java.util.UUID;

import onlexnet.fin2set.domain.models.BankStatement;

public interface BankStatementService {
    
    BankStatement getBankStatement(UUID accountID);

}
