package onlexnet.fin2set.domain.models;

import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Transaction {

  private String transactionId;

  private LocalDate bookingDate;

  private TransactionAmount transactionAmount;

  private String remittanceInformationUnstructured;

  @Data
  @Accessors(chain = true)
  public static class TransactionAmount {

    private double amount;

    private String currency;

  }

}
