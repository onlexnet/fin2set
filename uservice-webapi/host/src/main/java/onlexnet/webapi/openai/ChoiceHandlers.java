package onlexnet.webapi.openai;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.models.ChatChoice;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import onlexnet.webapi.domain.AppException;
import onlexnet.webapi.openai.Choice.ProcessResult;

/** Simple class to expose all Choice handlers as one invocable function. */
@RequiredArgsConstructor
@Component
public class ChoiceHandlers {

  private final List<Func> functions;

  private List<Choice.Handler> handlers;

  @PostConstruct
  public void init() {
    var functionsByNames = functions.stream().collect(Collectors.toMap(it -> it.functionName(), it -> it));
    handlers = List.of(new Choice.FunctionChoiceHandler(functionsByNames), new Choice.TransferResponseChoiceHandler());
  }

  ProcessResult process(ChatChoice chatChoice) {
    var handler = handlers.stream().filter(it -> it.canProcess(chatChoice)).findAny().orElseThrow(() -> new AppException("No known handler for " + chatChoice.toString()));
    return handler.process(chatChoice);
  }
}
