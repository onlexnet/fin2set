package onlexnet.webapi.openai;

import java.util.List;
import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import onlexnet.webapi.Application;
import onlexnet.webapi.LocalTest;
import onlexnet.webapi.bdd.Similarity;

@SpringBootTest(classes = { Application.class })
@LocalTest
public class OpenAiTest {

  @Autowired
  OpenAi openAi;

  @Test
  void openAiCanAnswer() {

    var initial = new Message("Respond only 'Hello'", MessageRole.USER);

    var response = openAi.getContinuation(List.of(initial), Locale.US);

    Assertions.assertThat(response).isEqualTo("Hello");
  }

  @Test
  void shouldUseApi2() {
    var query = "What’s the weather like in Boston?";
    var initial = new Message(query, MessageRole.USER);

    var response = openAi.getContinuation(List.of(initial), Locale.US);

    var a = openAi.getEmbedings(response);
    var b = openAi.getEmbedings("The weather in Boston is currently 35 degrees Celsius.");

    var similarity = Similarity.cosine(a, b);
    Assertions.assertThat(similarity).isGreaterThan(0.9);
  }

}
