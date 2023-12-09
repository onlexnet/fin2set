package onlexnet.webapi.openai;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import onlexnet.webapi.DaprExtension;
import onlexnet.webapi.openai.Message;
import onlexnet.webapi.openai.MessageRole;
import onlexnet.webapi.openai.OpenAi;

@SpringBootTest
@ExtendWith(DaprExtension.class)
public class OpenAiTest {

  @Autowired
  OpenAi openAi;

  @Test
  void openAiCanAnswer() {

    var initial = new Message("Respond only: 'Hello'", MessageRole.USER);

    var response = openAi.getContinuation(List.of(initial));

    Assertions.assertThat(response).isEqualTo("Hello");
  }
}
