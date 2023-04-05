package onlexnet.fin2set.domain.models;

import org.springframework.stereotype.Service;

@Service
public class OnlexBankStatemantMapper {

    public OnlexBankStatement toOnlexBankStatement(NordigenBankStatemant nordigenBankStatemant, AccountDTO account) {

        return new OnlexBankStatement()
        .setOwnerName(account.getOwnerName())
        .setDepositName(account.getId())
        .setBookedTransactions(nordigenBankStatemant.getTransactions().getBooked());
    }
    
}
