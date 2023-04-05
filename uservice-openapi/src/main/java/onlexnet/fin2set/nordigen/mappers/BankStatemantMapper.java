package onlexnet.fin2set.nordigen.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.Account;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.models.NordigenBankStatemant;

@UtilityClass
public class BankStatemantMapper {

    public static BankStatement fromDTO(NordigenBankStatemant nordigenBankStatemant, Account account) {
        return new BankStatement()
        .setOwnerName(account.getOwnerName())
        .setDepositName(account.getId())
        .setBookedTransactions(nordigenBankStatemant.getTransactions().getBooked());
    }
    
}
