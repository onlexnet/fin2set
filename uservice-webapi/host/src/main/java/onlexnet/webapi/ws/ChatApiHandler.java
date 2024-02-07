package onlexnet.webapi.ws;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.ws.api.ChatApiDelegate;

@Component
@RequiredArgsConstructor
class ChatApiHandler implements ChatApiDelegate {

  @Override
  public ResponseEntity<String> welcomeMessage() {
    return ResponseEntity.ok("Hello!");
  }
}
