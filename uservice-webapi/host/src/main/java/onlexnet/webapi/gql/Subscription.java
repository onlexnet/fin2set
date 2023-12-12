package onlexnet.webapi.gql;

import java.time.Duration;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import onlex.webapi.ViewGql;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
class Subscription {

  private final ReactiveSources reactiveSources;

  @SubscriptionMapping
  @SneakyThrows
  public Flux<ViewGql> view() {
    return reactiveSources.observed();
  }

  @SubscriptionMapping
  public Flux<Integer> ticks() {
    return Flux.range(0, Integer.MAX_VALUE)
        .delayElements(Duration.ofSeconds(1));
  }
}
