package dj.models;

import org.springframework.stereotype.Service;

import nordigen.AccountV2;

@Service
public class OnlexBankStatemantMapper {

    public OnlexBankStatement toOnlexBankStatement(NordigenBankStatemant nordigenBankStatemant, AccountV2 accountV2) {

        return new OnlexBankStatement()
        .setOwnerName(accountV2.getOwnerName())
        .setDepositName(accountV2.getId())
        .setBookedTransactions(nordigenBankStatemant.getTransactions().getBooked());
    }
    
}
