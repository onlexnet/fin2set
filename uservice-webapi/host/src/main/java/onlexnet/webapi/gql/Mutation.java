package onlexnet.webapi.gql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import graphql.schema.DataFetchingEnvironment;
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
class Mutation {

  private final OpenAi openai;
  private final LocaleResolver localeResolver;

  @MutationMapping
  @SneakyThrows
  MessageGql newMessage(@Argument List<MessageInputGql> messages, DataFetchingEnvironment env) {
      var locale = env.getLocale();
        
      var asDomain = messages.stream()
        .map(it -> new Message(it.text(), it.role() ==  RoleGql.USER ? MessageRole.USER : MessageRole.ASSISTANT))
        .toList();

    var response = openai.getContinuation(asDomain, locale);
    return new MessageGql(response);
  }
}
