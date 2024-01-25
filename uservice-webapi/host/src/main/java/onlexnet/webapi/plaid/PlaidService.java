package onlexnet.webapi.plaid;

import java.time.LocalDate;
import java.util.List;

public interface PlaidService {
  
  String createLinkToken();

  String exchangeLinkToken(String linkToken);

  List<Transaction> transactions(String accessToken, LocalDate start, LocalDate end);

  record Transaction(String id, double amount) { }
}
