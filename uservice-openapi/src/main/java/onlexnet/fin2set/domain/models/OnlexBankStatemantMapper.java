package onlexnet.fin2set.domain.models;

import org.springframework.stereotype.Service;

@Service
public class OnlexBankStatemantMapper {

    public BankStatement toBankStatement(NordigenBankStatemant nordigenBankStatemant, AccountDTO account) {

        return new BankStatement()
        .setOwnerName(account.getOwnerName())
        .setDepositName(account.getId())
        .setBookedTransactions(nordigenBankStatemant.getTransactions().getBooked());
    }
    
}
