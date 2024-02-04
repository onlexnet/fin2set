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
    var query = "What's the weather like in Boston in Celcius?";
    var initial = new Message(query, MessageRole.USER);

    var actual = openAi.getContinuation(List.of(initial), Locale.US);

    var actualAsVectors = openAi.getEmbedings(actual);
    var expected = "The weather in Boston is currently 35 degrees Celsius.";
    var expectedAsVectors = openAi.getEmbedings(expected);

    var similarity = Similarity.cosine(actualAsVectors, expectedAsVectors);
    Assertions.assertThat(similarity)
      .as("Actual vs Expected:\n[%s]\n <> \n[%s]", actual, expected)
      .isGreaterThan(0.9);
  }

}
