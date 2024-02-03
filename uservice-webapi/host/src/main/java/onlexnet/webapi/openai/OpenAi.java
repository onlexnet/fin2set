package onlexnet.webapi.openai;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestAssistantMessage;
import com.azure.ai.openai.models.ChatRequestFunctionMessage;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.ai.openai.models.CompletionsFinishReason;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.ai.openai.models.EmbeddingsUsage;
import com.azure.ai.openai.models.FunctionCallConfig;
import com.azure.ai.openai.models.FunctionDefinition;
import com.azure.core.credential.AzureKeyCredential;

import io.vavr.control.Either;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class OpenAi {

  private final OpenAiProperties props;
  private final List<Func> functions;

  private final String chatModel = "gpt-4-plugins";
  private final String embeddingsModel = "text-embedding-ada-002";

  private OpenAIClient client;
  private List<FunctionDefinition> functionDefs;

  @PostConstruct
  public void init() {
    client = new OpenAIClientBuilder().credential(new AzureKeyCredential(props.key())).endpoint(props.endpoint()).buildClient();
    functionDefs = functions.stream().map(it -> it.definition()).toList();
  }

  /**
   * Prepares full chat history, ready to send to OpenAI to get answer. It contains also list of available functions.
   */
  private ChatCompletionsOptions prepareChatHistory(List<Message> messages, Locale locale, Iterable<ChatRequestMessage> extraMessages) {
    var systemMessage = """
        You are limited to use only provided functions and tools
        and helping the user construct proper prompts to use available functions and tools.
        """ + "Keep conversation using language:" + locale.getLanguage();
    var asMessage = new ChatRequestSystemMessage(systemMessage);
    var messagesAsDto = messages.stream().map(Mapper::toDto);
    return new ChatCompletionsOptions(Stream.concat(Stream.concat(Stream.of(asMessage), messagesAsDto), StreamSupport.stream(extraMessages.spliterator(), false)).toList())
        // include list of available functions so that OpenAI is aware what may be used in conversation
        .setFunctions(functionDefs).setFunctionCall(FunctionCallConfig.AUTO)
        // Temperature is a parameter that controls the 'creativity' or randomness of
        // the text generated by GPT-3.
        // A higher temperature (e.g., 0.7) results in more diverse and creative output,
        // while a lower temperature (e.g., 0.2)
        // makes the output more deterministic and focused.
        // In practice, temperature affects the probability distribution over the
        // possible tokens at each step of the generation process.
        // A temperature of 0 would make the model completely deterministic, always
        // choosing the most likely token.
        // more: https://aipromptskit.com/openai-temperature-parameter/
        .setTemperature(0.)
        // Top_p sampling is an alternative to temperature sampling.
        // Instead of considering all possible tokens, GPT-3 considers only a subset of
        // tokens (the nucleus) whose cumulative probability
        // mass adds up to a certain threshold (top_p).
        // For example, if top_p is set to 0.1, GPT-3 will consider only the tokens that
        // make up the top 10% of the probability mass
        // for the next token. This allows for dynamic vocabulary selection based on
        // context.
        .setTopP(0.1);
  }

  /**
   * @param history client-side version of chat history
   * @param locale  helper to identify language used for conversation and settings
   * @return Assistant's continuation for given chat history.
   */
  public String getContinuation(List<Message> history, Locale locale) {

    // conver client-side history to AI-native object with added required metadata like functions etc
    var options = prepareChatHistory(history, locale, List.of());

    // get continuation of the chat from AI
    var chatCompletions = client.getChatCompletions(chatModel, options);

    var responseOptions = responseOptions(chatCompletions);

    var response = switch (responseOptions) {
      case Choice.ProcessResult.ForUser it -> it.message();
      case Choice.ProcessResult.ForAssistant it -> {
        var enchancedOptions = prepareChatHistory(history, locale, it.message());
        var enchancedCompletion = client.getChatCompletions(chatModel, enchancedOptions);
        // TODO implement scenario if AI asks second time about functions / tools
        // ATM we assume second answer does not contain ant request from AI to ask about functions / tools
        var responseEnchancedOptions = (Choice.ProcessResult.ForUser) responseOptions(enchancedCompletion);
        yield responseEnchancedOptions.message();
      }
    };

    var result = response.getContent();
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
    System.out.printf("Usage: number of prompt token is %d and number of total tokens in request and response is %d.%n", usage.getPromptTokens(), usage.getTotalTokens());
    return embedding;
  }

  private final ChoiceHandlers choiceHandlers;

  /**
   * @param completions proposed (by OpenAI) continuations of chat history
   * @param chatHistory
   * @return
   */
  @SneakyThrows
  private Choice.ProcessResult responseOptions(ChatCompletions completions) {
    // prompt result may return 1+ choices, but as I don't know how to use more
    // choices lets use only the first one
    var choices = completions.getChoices();
    var choice = choices.get(0);

    return choiceHandlers.process(choice);
  }

  // examples
  // https://cobusgreyling.medium.com/practical-examples-of-openai-function-calling-a6419dc38775
  // https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/openai/azure-ai-openai/src/samples/java/com/azure/ai/openai/usage/GetChatCompletionsToolCallSample.java
  // https://openai.com/blog/function-calling-and-other-api-updates
  // https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/openai/azure-ai-openai
  // https://github.com/Azure/azure-sdk-for-net/blob/main/sdk/openai/Azure.AI.OpenAI/README.md
}
