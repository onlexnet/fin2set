package onlexnet.webapi;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.server.WebGraphQlHandler;
import org.springframework.graphql.test.tester.GraphQlTester.Subscription;
import org.springframework.graphql.test.tester.WebGraphQlTester;

import lombok.SneakyThrows;
import onlex.webapi.ViewGql;
import reactor.core.publisher.Flux;

/** Set of methods used to test application functionlity. */
public class AppApi {

  private final WebGraphQlTester tester;
  private Subscription greetings;

  /** TBD. */
  @SneakyThrows
  public AppApi(String rootUri, String email, ExecutionGraphQlService executionGraphQlService) {
    var principalNameHeaderName = "X-MS-CLIENT-PRINCIPAL-NAME";
    var principalNameHeaderId = "X-MS-CLIENT-PRINCIPAL-ID";
    // var client = WebTestClient.bindToServer()
    //     .responseTimeout(Duration.ofMinutes(10))
    //     .baseUrl(rootUri + "/graphql")
    //     .defaultHeader(principalNameHeaderName, email)
    //     .defaultHeader(principalNameHeaderId, "principal-id")
    //     .build();
    var client2 = WebGraphQlHandler.builder(executionGraphQlService);

    tester = WebGraphQlTester.create(client2.build());

    greetings = tester.document("subscription { view }")
        .executeSubscription();
  }

  Flux<ViewGql> view() {
    return greetings.toFlux("view", ViewGql.class);
  }

}
