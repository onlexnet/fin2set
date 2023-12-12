package onlexnet.webapi;

import java.time.Duration;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.server.WebGraphQlHandler;
import org.springframework.graphql.test.tester.GraphQlTester.Subscription;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.graphql.test.tester.WebGraphQlTester;

import lombok.SneakyThrows;
import onlex.webapi.ViewGql;
import reactor.core.publisher.Flux;

/** Set of methods used to test application functionlity. */
public class AppApi {

  private final WebGraphQlTester wsTester;
  private final WebGraphQlTester httpTester;
  private Subscription viewSubscription;

  /** TBD. */
  @SneakyThrows
  public AppApi(String rootUri, String email, ExecutionGraphQlService executionGraphQlService) {
    var principalNameHeaderName = "X-MS-CLIENT-PRINCIPAL-NAME";
    var principalNameHeaderId = "X-MS-CLIENT-PRINCIPAL-ID";
    var httpClient = WebTestClient.bindToServer()
        .responseTimeout(Duration.ofMinutes(10))
        .baseUrl(rootUri + "/graphql")
        .defaultHeader(principalNameHeaderName, email)
        .defaultHeader(principalNameHeaderId, "principal-id")
        .build();
    var wsClient = WebGraphQlHandler.builder(executionGraphQlService);

    wsTester = WebGraphQlTester.create(wsClient.build());
    httpTester = HttpGraphQlTester.create(httpClient);

    viewSubscription = wsTester.documentName("ViewSubscription")
        .executeSubscription();
  }

  public Flux<ViewGql> view() {
    return viewSubscription.toFlux("view", ViewGql.class);
  }

  /** TBD. */
  public String say(String text) {
    return wsTester.documentName("sayMutation")
        .variable("text", text)
        .execute()
        .path("newMessage.text")
        .entity(String.class)
        .get();
  }

}
