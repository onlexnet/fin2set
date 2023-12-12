package onlexnet.webapi.openai;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.models.FunctionDefinition;
import com.azure.core.util.BinaryData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.experimental.Accessors;

@Component
@Accessors(fluent = true)
public class FuncWeather implements Func {

  @Getter
  private String functionName = "getCurrentWeather";

  public String description = "Get the current weather";

  @Getter
  public FunctionDefinition definition = new FunctionDefinition(functionName)
      .setDescription(description)
      .setParameters(BinaryData.fromObject(getFunctionDefinition2()));

  private static Map<String, Object> getFunctionDefinition2() {
    // Construct JSON in Map, or you can use create your own customized model.
    Map<String, Object> location = new HashMap<>();
    location.put("type", "string");
    location.put("description", "The city and state, e.g. San Francisco, CA");
    Map<String, Object> unit = new HashMap<>();
    unit.put("type", "string");
    unit.put("enum", Arrays.asList("celsius", "fahrenheit"));
    Map<String, Object> prop1 = new HashMap<>();
    prop1.put("location", location);
    prop1.put("unit", unit);
    Map<String, Object> functionDefinition = new HashMap<>();
    functionDefinition.put("type", "object");
    functionDefinition.put("required", Arrays.asList("location", "unit"));
    functionDefinition.put("properties", prop1);
    return functionDefinition;
  }

  @Override
  public String invoke(String args) {
    var weatherLocation = BinaryData.fromString(args).toObject(WeatherLocation.class);

    var currentWeather = getCurrentWeather(weatherLocation);

    var msg = String.format("The weather in %s is %d degrees %s.", weatherLocation.getLocation(), currentWeather,
        weatherLocation.getUnit());

    return msg;
  }

  // This is the method we offer to OpenAI to be used as a function_call.
  // For this example, we ignore the input parameter and return a simple value.
  private static int getCurrentWeather(WeatherLocation weatherLocation) {
    return 35;
  }

  // WeatherLocation is used for this sample. This describes the parameter of the
  // function you want to use.
  private static class WeatherLocation {
    @JsonProperty(value = "unit")
    String unit;
    @JsonProperty(value = "location")
    String location;

    @JsonCreator
    WeatherLocation(@JsonProperty(value = "unit") String unit, @JsonProperty(value = "location") String location) {
      this.unit = unit;
      this.location = location;
    }

    public String getUnit() {
      return unit;
    }

    public String getLocation() {
      return location;
    }
  }

}
