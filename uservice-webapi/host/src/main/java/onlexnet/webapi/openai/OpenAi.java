package onlexnet.webapi.openai;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
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
import com.azure.ai.openai.models.FunctionCallConfig;
import com.azure.ai.openai.models.FunctionDefinition;
import com.azure.core.credential.AzureKeyCredential;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import onlexnet.webapi.config.Secrets;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenAi {

  private final Secrets secrets;
  private final String chatModel = "gpt-4-plugins";
  private final String embeddingsModel = "text-embedding-ada-002";

  private final List<Func> functions;

  private List<FunctionDefinition> functionDefs;
  private Map<String, Func> functionsByNames;

  OpenAIClient client;

  @PostConstruct
  public void init() {
    client = new OpenAIClientBuilder()
        .credential(new AzureKeyCredential(secrets.openaiKey))
        .endpoint(secrets.openaiEndpoint)
        .buildClient();

    functionDefs = functions.stream().map(it -> it.definition()).toList();
    functionsByNames = functions.stream().collect(Collectors.toMap(it -> it.functionName(), it -> it));

  }

  public String getContinuation(List<Message> messages) {
    var dtoMessages = messages.stream()
        .map(it -> {
          if (it.role() == MessageRole.ASSISTANT) {
            return new ChatMessage(ChatRole.ASSISTANT, it.text());
          }
          if (it.role() == MessageRole.USER) {
            return new ChatMessage(ChatRole.USER, it.text());
          }
          throw new IllegalArgumentException();
        })
        .collect(Collectors.toList());

    var options = new ChatCompletionsOptions(dtoMessages)
        .setFunctions(functionDefs)
        .setFunctionCall(FunctionCallConfig.AUTO);

    var chatCompletions = client.getChatCompletions(chatModel, options);

    var chatMessages2 = handleFunctionCallResponse(chatCompletions.getChoices(), dtoMessages);

    // Take your function_call result as the input prompt to make another request to
    // service.
    var chatCompletionOptions2 = new ChatCompletionsOptions(chatMessages2)
        .setFunctions(functionDefs)
        .setFunctionCall(FunctionCallConfig.AUTO);

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

  @SneakyThrows
  private List<ChatMessage> handleFunctionCallResponse(List<ChatChoice> choices, List<ChatMessage> chatMessages) {
    for (ChatChoice choice : choices) {
      var choiceMessage = choice.getMessage();
      // We are looking for finish_reason = "function call".
      if (CompletionsFinishReason.FUNCTION_CALL.equals(choice.getFinishReason())) {

        var functionCall = choiceMessage.getFunctionCall();
        var functionName = functionCall.getName();
        var function = functionsByNames.get(functionName);

        log.info("Function name: {}, arguments: {}", functionCall.getName(), functionCall.getArguments());

        var msg1 = new ChatMessage(ChatRole.ASSISTANT, "").setFunctionCall(functionCall);
        chatMessages.add(msg1);

        var functionInvocationArgs = functionCall.getArguments();
        var result = function.invoke(functionInvocationArgs);
        var msg2 = new ChatMessage(ChatRole.FUNCTION, result).setName(functionName);
        chatMessages.add(msg2);

      } else {
        var messageHistory = new ChatMessage(ChatRole.ASSISTANT, choiceMessage.getContent());
        messageHistory.setFunctionCall(choiceMessage.getFunctionCall());
        chatMessages.add(messageHistory);

      }
    }
    return chatMessages;
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
