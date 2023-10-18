package onlexnet.webapi.gql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import onlex.webapi.MessageGql;
import onlex.webapi.MessageInputGql;
import onlex.webapi.RoleGql;
import onlexnet.webapi.openai.Message;
import onlexnet.webapi.openai.MessageRole;
import onlexnet.webapi.openai.OpenAi;

@Controller
@RequiredArgsConstructor
public class Mutation {

  private final OpenAi openai;

  @MutationMapping
  @SneakyThrows
  MessageGql newMessage(@Argument List<MessageInputGql> messages) {

    var asDomain = messages.stream()
      .map(it -> new Message(it.text(), it.role() ==  RoleGql.USER ? MessageRole.USER : MessageRole.ASSISTANT))
      .toList();

    var response = openai.getContinuation(asDomain);
    return new MessageGql(response);
  }
}
