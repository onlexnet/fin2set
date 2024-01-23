package onlexnet.webapi.ws;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.plaid.PlaidService;
import onlexnet.webapi.ws.api.CreateLinkTokenApiDelegate;
import onlexnet.webapi.ws.model.PlaidToken;

@Component
@RequiredArgsConstructor
class CreateLinkTokenApiImpl implements CreateLinkTokenApiDelegate {

  private final PlaidService plaidService;

  @Override
  public ResponseEntity<PlaidToken> apiCreateLinkTokenGet() {
    var linkToken = plaidService.createLinkToken();
    var result = new PlaidToken().value(linkToken);
    return ResponseEntity.ok(result);
  }
}
