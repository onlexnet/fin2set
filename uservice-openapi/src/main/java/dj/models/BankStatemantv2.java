
package dj.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankStatemantv2 {

    @JsonProperty("transactions")
    public Transactions transactions;

    public static class Transactions {

        @JsonProperty("booked")
        public List<Booked> booked = new ArrayList<>();

    }

    public static class Booked {

        @JsonProperty("transactionId")
        public String transactionId;

        @JsonProperty("bookingDate")
        public String bookingDate;

        @JsonProperty("transactionAmount")
        public TransactionAmount transactionAmount;

        @JsonProperty("remittanceInformationUnstructured")
        public String remittanceInformationUnstructured;

        
    }

    public static class TransactionAmount {

        @JsonProperty("amount")
        public String amount;

        @JsonProperty("currency")
        public String currency;

    }

}
