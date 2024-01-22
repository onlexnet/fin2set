package onlexnet.webapi.ws;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import onlexnet.webapi.ws.api.CreateLinkTokenApiDelegate;

@Component
class CreateLinkTokenApiImpl implements CreateLinkTokenApiDelegate {
  
  @Override
  public ResponseEntity<String> apiCreateLinkTokenGet() {

    return ResponseEntity.ok("Hello");
  }

}
