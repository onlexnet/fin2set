package dj.services.onlex.bankstatement;

import dj.models.OnlexBankStatement;

public interface OnlexBankStatementService {
    
    OnlexBankStatement getOnlexBankStatement(String accountID);

}
