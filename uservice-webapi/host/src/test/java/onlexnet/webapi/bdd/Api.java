package onlexnet.webapi.bdd;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlex.webapi.ViewGql;
import onlexnet.webapi.AppApi;
import onlexnet.webapi.domain.ValName;
import reactor.core.Disposable;
import reactor.core.Disposables;

@Component
@RequiredArgsConstructor
public class Api {

  private final ExecutionGraphQlService executionGraphQlService;

  record Context(Facts facts, AppApi session, Disposable.Composite disposed) {
  }

  private final Map<ValName, Context> facts = new ConcurrentHashMap<ValName, Context>();

  public Facts get(ValName userAlias) {
    var ctx = getOrCreate(userAlias);
    return ctx.facts();
  }

  public AppApi act(ValName userAlias) {
    var ctx = getOrCreate(userAlias);
    return ctx.session();
  }

  private Context getOrCreate(ValName userAlias) {
    return facts.computeIfAbsent(userAlias, key -> {
      var result = new Facts();
      var session = new AppApi("graphiql", "slawomir@siudek.net", executionGraphQlService);
      var disposable = Disposables.composite();
      
      disposable.addAll(List.of(
        session.view().subscribe(it -> {
          result.on(new Facts.On.OnViewEvent(it));
        })));

      return new Context(result, session, disposable);
    });
  }

}
