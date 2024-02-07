package onlexnet.webapi.ws;

import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.openai.Message;
import onlexnet.webapi.openai.MessageRole;
import onlexnet.webapi.openai.OpenAi;
import onlexnet.webapi.ws.api.ChatApiDelegate;

@Component
@RequiredArgsConstructor
class ChatApiHandler implements ChatApiDelegate {

  private final OpenAi ai;

  @Override
  public ResponseEntity<String> welcomeMessage() {
    var initialUserMessageToGetInitialAssistantMessage = "What you can do for me?";
    var initialUserMessage = new Message(initialUserMessageToGetInitialAssistantMessage, MessageRole.USER);
    var locale = LocaleContextHolder.getLocale();
    var assistantResponse = ai.getContinuation(List.of(initialUserMessage), locale);
    return ResponseEntity.ok(assistantResponse);
  }
}
