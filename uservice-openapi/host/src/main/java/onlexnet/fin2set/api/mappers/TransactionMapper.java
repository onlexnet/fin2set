package onlexnet.fin2set.api.mappers;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.Transaction;

@UtilityClass
public class TransactionMapper {

  public static onlexnet.fin2set.generated.dto.Transaction toDTO(Transaction transaction) {
    var transactionDTO = new onlexnet.fin2set.generated.dto.Transaction();
    transactionDTO.setTransactionId(transaction.getTransactionId());
    transactionDTO.setBookingDate(transaction.getBookingDate());
    transactionDTO.setRemittanceInformationUnstructured(transaction.getRemittanceInformationUnstructured());
    transactionDTO.setTransactionAmount(toDTO(transaction.getTransactionAmount()));
    return transactionDTO;
  }

  public static List<onlexnet.fin2set.generated.dto.Transaction> toDTO(List<Transaction> transactionList) {
    var list = new ArrayList<onlexnet.fin2set.generated.dto.Transaction>();
    for (Transaction transaction : transactionList) {
      list.add(toDTO(transaction));
    }
    return list;
  }

  private onlexnet.fin2set.generated.dto.TransactionAmount toDTO(Transaction.TransactionAmount transactionAmount) {
    var transactionAmountDTO = new onlexnet.fin2set.generated.dto.TransactionAmount();
    transactionAmountDTO.setAmount(transactionAmount.getAmount());
    transactionAmountDTO.setCurrency(transactionAmount.getCurrency());
    return transactionAmountDTO;
  }
  
}
