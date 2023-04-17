package onlexnet.fin2set.nordigen.service.functionality;

import java.util.UUID;

import onlexnet.fin2set.domain.models.BankStatement;

public interface BankStatementService {
    
    BankStatement getBankStatement(UUID accountID);

}
