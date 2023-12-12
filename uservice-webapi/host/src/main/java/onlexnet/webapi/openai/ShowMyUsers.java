package onlexnet.webapi.openai;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.models.FunctionDefinition;

import lombok.RequiredArgsConstructor;
import onlex.webapi.ViewGql;
import onlexnet.webapi.gql.ReactiveSources;

@Component
@RequiredArgsConstructor
public class ShowMyUsers {

  private final ReactiveSources reactiveSources;

  public String internalFunctionName = "ShowMyClient";

  public String description = "Get the list of clients";

  public FunctionDefinition functionDefinition = new FunctionDefinition(internalFunctionName)
      .setDescription(description)
      .setParameters(noParams());
      // .setParameters("{ }");
      // .setParameters(BinaryData.fromObject(getFunctionDefinition()));

  public Map<String, Object> getFunctionDefinition() {
    // Construct JSON in Map, or you can use create your own customized model.
    Map<String, Object> location = new HashMap<>();
    // location.put("type", "string");
    // location.put("description", "The city and state, e.g. San Francisco, CA");
    // Map<String, Object> unit = new HashMap<>();
    // unit.put("type", "string");
    // unit.put("enum", Arrays.asList("celsius", "fahrenheit"));
    Map<String, Object> prop1 = new HashMap<>();
    // prop1.put("location", location);
    // prop1.put("unit", unit);
    var functionDefinition = new HashMap<String, Object>();
    // functionDefinition.put("type", "object");
    // functionDefinition.put("required", Arrays.asList("location", "unit"));
    functionDefinition.put("properties", prop1);
    return functionDefinition;
  }

  public static Map<String, Object> noParams() {
    // Construct JSON in Map, or you can use create your own customized model.
    Map<String, Object> prop1 = new HashMap<>();
    var functionDefinition = new HashMap<String, Object>();
    functionDefinition.put("type", "object");
    functionDefinition.put("required", Arrays.asList());
    functionDefinition.put("properties", prop1);
    return functionDefinition;
  }

  public void invoke() {
    reactiveSources.publish(ViewGql.VIEW1);
  }
}
