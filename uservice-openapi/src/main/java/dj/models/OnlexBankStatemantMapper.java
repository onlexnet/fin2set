package dj.models;

import org.springframework.stereotype.Service;

import dj.models.dto.AccountDTO;

@Service
public class OnlexBankStatemantMapper {

    public OnlexBankStatement toOnlexBankStatement(NordigenBankStatemant nordigenBankStatemant, AccountDTO account) {

        return new OnlexBankStatement()
        .setOwnerName(account.getOwnerName())
        .setDepositName(account.getId())
        .setBookedTransactions(nordigenBankStatemant.getTransactions().getBooked());
    }
    
}
