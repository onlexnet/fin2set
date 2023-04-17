package onlexnet.fin2set.nordigen.models.mappers;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.Transaction;
import onlexnet.fin2set.nordigen.models.NordigenBankStatemant.Booked;

@UtilityClass
public class TransactionMapper {

  public static Transaction fromDTO(Booked booked) {
    return new Transaction()
    .setTransactionId(booked.getTransactionId())
    .setBookingDate(booked.getBookingDate())
    .setRemittanceInformationUnstructured(booked.getRemittanceInformationUnstructured())
    .setTransactionAmount(new Transaction.TransactionAmount()
    .setAmount(booked.getTransactionAmount().getAmount())
    .setCurrency(booked.getTransactionAmount().getCurrency()));
  }

  public static List<Transaction> fromDTO(List<Booked> listBooked) {
    List<Transaction> list = new ArrayList<>(null);
    for (Booked booked : listBooked) {
      var transaction = fromDTO(booked);
      list.add(transaction);
    }
    return list;
  }

}
