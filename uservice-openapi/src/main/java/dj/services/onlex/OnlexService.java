package dj.services.onlex;

import dj.models.OnlexBankStatement;

public interface OnlexService {
    
    OnlexBankStatement getOnlxBankStatement(String accountID);

}
