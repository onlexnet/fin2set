package onlexnet.webapi.openai;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.ai.openai.models.FunctionCallConfig;
import com.azure.core.credential.AzureKeyCredential;

import jakarta.annotation.PostConstruct;
import onlexnet.webapi.config.Secrets;

@Component
public class OpenAi {

  @Autowired
  Secrets secrets;

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

    // FunctionCallConfig functionCallConfig = new FunctionCallConfig("calc");
    // options.setFunctionCall(functionCallConfig);

    var cc = client.getChatCompletions("text-turbo", options);
    var choice = cc.getChoices().getFirst();
    return choice.getMessage().getContent();
  }

}