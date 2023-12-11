package onlexnet.webapi.bdd;

import java.time.Duration;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlex.webapi.ViewGql;
import onlexnet.webapi.AppApi;

@Component
@RequiredArgsConstructor
public class Api {

  private final ExecutionGraphQlService executionGraphQlService;

  public ViewGql getTick(Facts facts) {
      var api = new AppApi("graphiql", "slawomir@siudek.net", executionGraphQlService);
      return api.view().blockFirst(Duration.ofSeconds(3));
  }

}
