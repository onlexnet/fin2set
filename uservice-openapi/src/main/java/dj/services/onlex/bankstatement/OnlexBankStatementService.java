package dj.services.onlex.bankstatement;

import java.util.UUID;

import dj.models.OnlexBankStatement;

public interface OnlexBankStatementService {
    
    OnlexBankStatement getOnlexBankStatement(UUID accountID);

}
