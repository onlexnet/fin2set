package onlexnet.webapi.client;

import static java.util.Optional.ofNullable;

import java.time.Duration;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.server.WebGraphQlHandler;
import org.springframework.graphql.test.tester.GraphQlTester.Subscription;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import onlex.webapi.ViewGql;
import onlexnet.webapi.client.ApiClient;
import onlexnet.webapi.client.api.ChatApi;
import reactor.core.Disposable;
import reactor.core.Disposables;

/** Single class to expose all available application API (OAS3 and GraphQL) as set of methods. */
@Slf4j
public class SessionApi implements AutoCloseable {

  private final WebGraphQlTester wsTester;
  private final WebGraphQlTester httpTester;
  private Subscription viewSubscription;
  private final ApiMutationCallbacks callbacks;
  private final Disposable.Composite instanceDisposer = Disposables.composite();

  private ChatApi chatApi;

  /** TBD. */
  @SneakyThrows
  public SessionApi(String serverAddress, String rootUri, String email, ExecutionGraphQlService executionGraphQlService, ApiMutationCallbacks callbacks) {
    this.callbacks = callbacks;
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
    var disposed = viewSubscription
        .toFlux("view", ViewGql.class)
        .subscribe(it -> callbacks.viewChanged(it));
    instanceDisposer.add(disposed);

    // creation of http client
    var versioned = serverAddress + "/v1";
    log.info("Sparta: {}", versioned);
    var apiClient = new ApiClient()
        .setBasePath(versioned);
    chatApi = new ChatApi(apiClient);
  }


  /** TBD. */
  public String say(String text) {
    var result = wsTester.documentName("sayMutation")
        .variable("text", text)
        .execute()
        .path("newMessage.text")
        .entity(String.class)
        .get();
    callbacks.afterSay(result);
    return result;
  }

  public String getWelcomeMessge() {
    return ofNullable(chatApi.welcomeMessage().getValue()).orElse(null);
  }

  /**
   * Invoked when gql methods are finished.
   * Used to handle methods invocationa and update internal view of known incocations to build vision of facts tor assersions client <-> server.
   */
  public interface ApiMutationCallbacks {

    void afterSay(String result);

    void viewChanged(ViewGql newValue);

  }

  /** TBD. */
  public static class NoOpApiCallbacks implements ApiMutationCallbacks {

    @Override
    public void afterSay(String result) {
    }

    @Override
    public void viewChanged(ViewGql newValue) {
    }

  }

  @Override
  public void close() {
    instanceDisposer.dispose();
  }

}
