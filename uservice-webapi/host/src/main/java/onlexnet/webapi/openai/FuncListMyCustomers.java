package onlexnet.webapi.openai;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.azure.ai.openai.models.FunctionDefinition;
import com.azure.core.util.BinaryData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import onlexnet.webapi.domain.ViewChange;
import onlexnet.webapi.domain.ViewChange.Type;

@Component
@RequiredArgsConstructor
@Accessors(fluent = true)
public class FuncListMyCustomers implements Func {

  private final ApplicationEventPublisher publisher;

  @Getter
  private String functionName = "ListMyCustomers";

  public String description = "Get the list of customers";

  @Getter
  public FunctionDefinition definition = new FunctionDefinition(functionName)
      .setDescription(description)
      .setParameters(BinaryData.fromObject(noParams()));

  public static Map<String, Object> noParams() {
    // Construct JSON in Map, or you can use create your own customized model.
    Map<String, Object> prop1 = new HashMap<>();
    var functionDefinition = new HashMap<String, Object>();
    functionDefinition.put("type", "object");
    functionDefinition.put("required", Arrays.asList());
    functionDefinition.put("properties", prop1);
    return functionDefinition;
  }

  @Override
  public String invoke(String args) {
    // the message should be postponed when whole operation succedeed
    // hence Application transactional events need to be used.
    publisher.publishEvent(new ViewChange.Notification(Type.VIEW1));

    return """
      The list of customers consists of:
      - Client1
      - Client2
      """;
  }
}
