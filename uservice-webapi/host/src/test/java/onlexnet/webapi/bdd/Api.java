package onlexnet.webapi.bdd;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
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
      var facts = new Facts();

      var callbacks = new AppApi.ApiCallbacks() {
        @Override
        public void afterSay(String result) {
          facts.on(new Facts.On.ChatLastResult(result));
        }
      };
      
      var session = new AppApi("graphiql", "slawomir@siudek.net", executionGraphQlService, callbacks);
      var disposable = Disposables.composite();

      disposable.addAll(List.of(
        session.view().subscribe(it -> {
          facts.on(new Facts.On.ViewEvent(it));
        })));

      return new Context(facts, session, disposable);
    });
  }

}
