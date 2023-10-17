package onlexnet.webapi.gql;

import java.time.LocalTime;
import java.util.Random;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import lombok.SneakyThrows;
import onlex.webapi.MessageGql;
import onlex.webapi.MessageInputGql;

@Controller
public class Mutation {
  
  Random random = new Random();

  @MutationMapping
  @SneakyThrows
  MessageGql newMessage(@Argument MessageInputGql message) {
    var delayInSecs = random.nextInt(3);
    Thread.sleep(delayInSecs * 1000);
    var text = message.text();
    var time = LocalTime.now().withNano(0).toString();
    return new MessageGql("Response [" + time + "]: " + text);
  }
}
