package onlexnet.webapi.ws;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.plaid.PlaidService;
import onlexnet.webapi.ws.api.TransactionsApiDelegate;
import onlexnet.webapi.ws.model.AccessToken;
import onlexnet.webapi.ws.model.TransactionDTO;

@Component
@RequiredArgsConstructor
class TransactionsApiImpl implements TransactionsApiDelegate {

  private final PlaidService plaidService;

  // Fetches balance data using the Node client library for Plaid
  @Override
  public ResponseEntity<List<TransactionDTO>> transactions(AccessToken body) {
    var now = LocalDate.now();
    var startDate = now.minusDays(30);
    var endDate = now;

    var data = plaidService.transactions(body.getAccessToken(), startDate, endDate);
    var result = data.stream()
        .map(it -> new TransactionDTO(it.id(), it.amount()))
        .toList();
    return ResponseEntity.ok(result);
  }

}
