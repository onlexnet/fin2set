package onlexnet.webapi.gql;

import java.time.Duration;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import onlex.webapi.ViewGql;
import reactor.core.publisher.Flux;

@Controller
public class Subscription {

  @SubscriptionMapping
  public Flux<ViewGql> view() {
    return Flux.just(ViewGql.CHAT);
  }

  @SubscriptionMapping
  public Flux<Integer> ticks() {
    return Flux.range(0, Integer.MAX_VALUE)
        .delayElements(Duration.ofSeconds(1));
  }
}
