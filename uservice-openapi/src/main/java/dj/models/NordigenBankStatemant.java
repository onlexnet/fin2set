
package dj.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NordigenBankStatemant {

    @JsonProperty("transactions")
    public Transactions transactions;

    @Data
    @Accessors(chain = true)
    public static class Transactions {

        @JsonProperty("booked")
        public List<Booked> booked = new ArrayList<>();

    }

    @Data
    @Accessors(chain = true)
    public static class Booked {

        @JsonProperty("transactionId")
        public String transactionId;

        @JsonProperty("bookingDate")
        public LocalDate bookingDate;

        @JsonProperty("transactionAmount")
        public TransactionAmount transactionAmount;

        @JsonProperty("remittanceInformationUnstructured")
        public String remittanceInformationUnstructured;

    }

    @Data
    @Accessors(chain = true)
    public static class TransactionAmount {

        @JsonProperty("amount")
        public double amount;

        @JsonProperty("currency")
        public String currency;

    }

}
