package onlexnet.webapi.ws;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.plaid.PlaidService;
import onlexnet.webapi.ws.api.CreateLinkTokenApiDelegate;
import onlexnet.webapi.ws.model.LinkToken;

@Component
@RequiredArgsConstructor
class CreateLinkTokenApiImpl implements CreateLinkTokenApiDelegate {

  private final PlaidService plaidService;

  @Override
  public ResponseEntity<LinkToken> createLinkToken() {
    var linkToken = plaidService.createLinkToken();
    var result = new LinkToken().linkToken(linkToken);
    return ResponseEntity.ok(result);
  }
}

// // Fetches balance data using the Node client library for Plaid
// app.get("/api/transactions", async (req, res, next) => {
//   const access_token = req.session.access_token;
//   const startDate = moment().subtract(30, "days").format("YYYY-MM-DD");
//   const endDate = moment().format("YYYY-MM-DD");

//   const transactionResponse = await client.transactionsGet({
//     access_token: access_token,
//     start_date: startDate,
//     end_date: endDate,
//     options: { count: 10 },
//   });
//   res.json(transactionResponse.data);
// });
