package onlexnet.fin2set.nordigen.models.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.generated.Account;
import onlexnet.fin2set.nordigen.models.NordigenBankStatemant;

@UtilityClass
public class BankStatemantMapper {

    public static BankStatement fromDTO(NordigenBankStatemant nordigenBankStatemant, Account account) {
        return new BankStatement()
        .setOwnerName(account.getOwnerName())
        .setDepositName(account.getId())
        .setBookedTransactions(TransactionMapper.fromDTO(nordigenBankStatemant.getTransactions().booked));
    }
    
}
