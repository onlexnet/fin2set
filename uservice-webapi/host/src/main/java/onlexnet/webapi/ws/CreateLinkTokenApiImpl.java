package onlexnet.webapi.ws;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import onlexnet.webapi.ws.api.CreateLinkTokenApiDelegate;
import onlexnet.webapi.ws.model.PlaidToken;

@Component
class CreateLinkTokenApiImpl implements CreateLinkTokenApiDelegate {
  @Override
  public ResponseEntity<PlaidToken> apiCreateLinkTokenGet() {
    var result = new PlaidToken().value("Hello!");
    return ResponseEntity.ok(result);
  }
}
