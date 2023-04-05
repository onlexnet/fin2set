package onlexnet.fin2set.domain.bankstatement;

import java.util.UUID;

import onlexnet.fin2set.domain.models.OnlexBankStatement;

public interface OnlexBankStatementService {
    
    OnlexBankStatement getOnlexBankStatement(UUID accountID);

}
