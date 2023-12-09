package onlexnet.webapi.openai;

import java.util.List;

import org.springframework.stereotype.Component;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.OpenAIServiceVersion;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.core.credential.AzureKeyCredential;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import onlexnet.webapi.config.Secrets;

@Component
@RequiredArgsConstructor
public class OpenAi {

  private final Secrets secrets;
  private final String deploymentName = "gpt-4-plugins";

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
        .toList();
    var options = new ChatCompletionsOptions(dtoMessages);

    var cc = client.getChatCompletions(deploymentName, options);
    var choice = cc.getChoices().get(0);
    return choice.getMessage().getContent();
  }

}
