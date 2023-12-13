package onlexnet.webapi;

import java.time.Duration;

import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.server.WebGraphQlHandler;
import org.springframework.graphql.test.tester.GraphQlTester.Subscription;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import lombok.SneakyThrows;
import onlex.webapi.ViewGql;
import reactor.core.Disposable;
import reactor.core.Disposables;

/** Set of methods used to test application functionlity. */
public class AppApi implements AutoCloseable {

  private final WebGraphQlTester wsTester;
  private final WebGraphQlTester httpTester;
  private Subscription viewSubscription;
  private final ApiCallbacks callbacks;
  private final Disposable.Composite instanceDisposer = Disposables.composite();

  /** TBD. */
  @SneakyThrows
  public AppApi(String rootUri, String email, ExecutionGraphQlService executionGraphQlService, ApiCallbacks callbacks) {
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

  /** Invoked when gql methods are finished. */
  public interface ApiCallbacks {

    void afterSay(String result);

    void viewChanged(ViewGql newValue);

  }

  /** TBD. */
  public static class NoOpApiCallbacks implements ApiCallbacks {

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
