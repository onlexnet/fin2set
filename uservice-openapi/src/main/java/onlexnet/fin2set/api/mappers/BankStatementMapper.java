package onlexnet.fin2set.api.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.BankStatement;

@UtilityClass
public class BankStatementMapper {

  public static onlexnet.fin2set.generated.dto.BankStatement toDTO(BankStatement bankStatement) {
    var bankStatementDTO = new onlexnet.fin2set.generated.dto.BankStatement();
    bankStatementDTO.setOwnerName(bankStatement.getOwnerName());
    bankStatementDTO.setDepositName(bankStatement.getDepositName());
    bankStatementDTO.setBookedTransactions(TransactionMapper.toDTO(bankStatement.getBookedTransactions()));
    return bankStatementDTO;
  }
  
}
