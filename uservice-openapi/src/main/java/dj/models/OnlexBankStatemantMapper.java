package dj.models;

import org.springframework.stereotype.Service;

import nordigen.Account;

@Service
public class OnlexBankStatemantMapper {

    public OnlexBankStatement toOnlexBankStatement(NordigenBankStatemant nordigenBankStatemant, Account Account) {

        return new OnlexBankStatement()
        .setOwnerName(Account.getOwnerName())
        .setDepositName(Account.getId())
        .setBookedTransactions(nordigenBankStatemant.getTransactions().getBooked());
    }
    
}
