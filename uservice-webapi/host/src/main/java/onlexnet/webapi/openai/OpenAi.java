package onlexnet.webapi.openai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.OpenAIServiceVersion;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.ai.openai.models.CompletionsFinishReason;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.ai.openai.models.EmbeddingsUsage;
import com.azure.ai.openai.models.FunctionCall;
import com.azure.ai.openai.models.FunctionCallConfig;
import com.azure.ai.openai.models.FunctionDefinition;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import onlexnet.webapi.config.Secrets;

@Component
@RequiredArgsConstructor
public class OpenAi {

  private final Secrets secrets;
  private final String chatModel = "gpt-4-plugins";
  private final String embeddingsModel = "text-embedding-ada-002";

  private static final String wheatherFunctionName = "getCurrentWeather";
  OpenAIClient client;

  @PostConstruct
  public void init() {
    client = new OpenAIClientBuilder()
        .credential(new AzureKeyCredential(secrets.openaiKey))
        .endpoint(secrets.openaiEndpoint)
        .buildClient();

  }

  public String getContinuation(List<Message> messages) {
    var dtoMessages = messages.stream()
        .map(it -> {
          var dtoRole = it.role() == MessageRole.ASSISTANT ? ChatRole.ASSISTANT : ChatRole.USER;
          return new ChatMessage(dtoRole, it.text());
        })
        .collect(Collectors.toList());

    var functions = Arrays.asList(
        new FunctionDefinition(wheatherFunctionName)
            .setDescription("Get the current weather")
            .setParameters(BinaryData.fromObject(getFunctionDefinition())));

    var options = new ChatCompletionsOptions(dtoMessages)
        .setFunctionCall(FunctionCallConfig.AUTO)
        .setFunctions(functions);

    var chatCompletions = client.getChatCompletions(chatModel, options);

    var chatMessages2 = handleFunctionCallResponse(chatCompletions.getChoices(), dtoMessages);

    // Take your function_call result as the input prompt to make another request to
    // service.
    ChatCompletionsOptions chatCompletionOptions2 = new ChatCompletionsOptions(chatMessages2);
    ChatCompletions chatCompletions2 = client.getChatCompletions(chatModel, chatCompletionOptions2);
    List<ChatChoice> choices = chatCompletions2.getChoices();
    var message = choices.get(0).getMessage();
    var result = message.getContent();
    return result;
  }

  // hint:
  // https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/openai/azure-ai-openai/src/samples/java/com/azure/ai/openai/usage/GetEmbeddingsSample.java
  public List<Double> getEmbedings(String text) {
    var embeddingsOptions = new EmbeddingsOptions(Arrays.asList(text));
    Embeddings embeddings = client.getEmbeddings(embeddingsModel, embeddingsOptions);

    EmbeddingItem item = embeddings.getData().get(0);
    // System.out.printf("Index: %d.%n", item.getPromptIndex());
    var embedding = item.getEmbedding();

    EmbeddingsUsage usage = embeddings.getUsage();
    System.out.printf(
        "Usage: number of prompt token is %d and number of total tokens in request and response is %d.%n",
        usage.getPromptTokens(), usage.getTotalTokens());
    return embedding;
  }

  private static Map<String, Object> getFunctionDefinition() {
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

  private static List<ChatMessage> handleFunctionCallResponse(List<ChatChoice> choices,
      List<ChatMessage> chatMessages) {
    for (ChatChoice choice : choices) {
      ChatMessage choiceMessage = choice.getMessage();
      FunctionCall functionCall = choiceMessage.getFunctionCall();
      // We are looking for finish_reason = "function call".
      if (CompletionsFinishReason.FUNCTION_CALL.equals(choice.getFinishReason())) {
        // We call getCurrentWeather() and pass the result to the service.
        System.out.printf("Function name: %s, arguments: %s.%n", functionCall.getName(), functionCall.getArguments());
        // WeatherLocation is our class that represents the parameters to use in our
        // function call.
        // We deserialize and pass it to our function.
        WeatherLocation weatherLocation = BinaryData.fromString(functionCall.getArguments())
            .toObject(WeatherLocation.class);

        int currentWeather = getCurrentWeather(weatherLocation);
        


        var msg1 = new ChatMessage(ChatRole.ASSISTANT, "").setFunctionCall(functionCall);
        chatMessages.add(msg1);

        var msg2 = new ChatMessage(ChatRole.FUNCTION, String.format("The weather in %s is %d degrees %s.",
            weatherLocation.getLocation(), currentWeather, weatherLocation.getUnit()))
            .setName(wheatherFunctionName);
        chatMessages.add(msg2);
      } else {
        var messageHistory = new ChatMessage(ChatRole.ASSISTANT, choiceMessage.getContent());
        messageHistory.setFunctionCall(choiceMessage.getFunctionCall());
        chatMessages.add(messageHistory);
      }
    }
    return chatMessages;
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

  // examples
  // https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/openai/azure-ai-openai/src/samples/java/com/azure/ai/openai/FunctionCallSample.java
  // https://learn.microsoft.com/en-us/java/api/com.azure.ai.openai.models.functiondefinition?view=azure-java-preview
  // https://cobusgreyling.medium.com/practical-examples-of-openai-function-calling-a6419dc38775
  // https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/openai/azure-ai-openai/src/samples/java/com/azure/ai/openai/usage/GetChatCompletionsToolCallSample.java
  // https://openai.com/blog/function-calling-and-other-api-updates
  // https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/openai/azure-ai-openai
  // https://github.com/Azure/azure-sdk-for-net/blob/main/sdk/openai/Azure.AI.OpenAI/README.md
  // "name": "order_detail",
  // "description": "template to capture an order.",
  // "parameters": {
  // "type": "object",
  // "properties": {
  // "to_address": {
  // "type": "string",
  // "description": "To address for the delivery"
  // },
  // "order": {
  // "type": "string",
  // "description": "The detail of the order"
  // },
  // "date": {
  // "type": "string",
  // "description": "the date for delivery."
  // },
  // "notes": {
  // "type": "string",
  // "description": "Any delivery notes."
  // }
  // }
  // }
  // }
}
