package onlexnet.webapi.ws;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.plaid.PlaidService;
import onlexnet.webapi.ws.api.ExchangeLinkTokenApiDelegate;
import onlexnet.webapi.ws.model.AccessToken;
import onlexnet.webapi.ws.model.PublicToken;

@Component
@RequiredArgsConstructor
class ExchangeLinkTokenApiImpl implements ExchangeLinkTokenApiDelegate {

  private final PlaidService plaidService;

  // Exchanges the public token from Plaid Link for an access token
  @Override
  public ResponseEntity<AccessToken> exchangeLinkToken(PublicToken body) {
    // FOR DEMO PURPOSES ONLY
    // You should really store access tokens in a database that's tied to your
    // authenticated user id.
    var linkToken = body.getPublicToken();
    var result = plaidService.exchangeLinkToken(linkToken);
    var resultAsToken = new AccessToken().accessToken(result);
    return ResponseEntity.ok(resultAsToken);
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
