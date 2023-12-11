package onlexnet.webapi.bdd;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlex.webapi.ViewGql;
import onlexnet.webapi.AppApi;
import onlexnet.webapi.domain.ValName;

@Component
@RequiredArgsConstructor
public class Api {

  private final ExecutionGraphQlService executionGraphQlService;

  record Context(Facts facts, AppApi session) {
  }

  private final Map<ValName, Context> facts = new ConcurrentHashMap<ValName, Context>();

  public ViewGql getTick(ValName userAlias) {
    var ctx = getOrCreate(userAlias);
    return ctx.session.view().blockFirst(Duration.ofSeconds(3));
  }

  public Facts get(ValName userAlias) {
    var ctx = getOrCreate(userAlias);
    return ctx.facts();
  }

  private Context getOrCreate(ValName userAlias) {
    return facts.computeIfAbsent(userAlias, key -> {
      var result = new Facts();
      var session = new AppApi("graphiql", "slawomir@siudek.net", executionGraphQlService);
      return new Context(result, session);
    });
  }

}
