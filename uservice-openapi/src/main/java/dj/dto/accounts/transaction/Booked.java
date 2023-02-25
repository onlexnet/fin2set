package dj.dto.accounts.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class Booked {

    private String transactionId;

    private String bookingDate;

    private String valueDate;

    private TransactionAmount transactionAmount;

    private String remittanceInformationUnstructured;

    private String bankTransactionCode;

    private String debtorName;

    private DebtorAccount debtorAccount;

}
