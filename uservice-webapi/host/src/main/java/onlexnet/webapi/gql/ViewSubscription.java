package onlexnet.webapi.gql;

import java.time.Duration;

import org.springframework.context.event.EventListener;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import onlex.webapi.ViewGql;
import onlexnet.webapi.domain.ViewChange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Controller
@RequiredArgsConstructor
/** Implementation of subscriptions defined in view.graphql file. */
class ViewSubscription implements AutoCloseable {

  private static final int BUFFERRING_TIMEOUT = 100;
  private Many<ViewGql> sink;

  /**
   * Post-construct method. please invoke manually in tests where Spring is not
   * creating the Component.
   */
  @PostConstruct
  public void init() {
    sink = Sinks.many().multicast().<ViewGql>onBackpressureBuffer();

    // We open first subscription so emitter can have minimum one listener.
    // Without that trick, without first listener Emitter will block after some time
    // when internal
    // buffer is full and will stop working when the last subscriber is off.
    // Because the emitter is now shared, we would like to have it working
    // independently of subscribers
    // so we keep one subscriber alive.
    sink.asFlux().subscribe();
  }

  @SubscriptionMapping
  @SneakyThrows
  public Flux<ViewGql> view() {
    return sink.asFlux().sample(Duration.ofMillis(BUFFERRING_TIMEOUT)).startWith(ViewGql.CHAT);
  }

  @Override
  public void close() {
    sink.tryEmitComplete();
  }

  /**
   * Converts Notification to something more appropriate for Graphql.
   * 
   * @param notification GenericProgressNotification.
   */
  @EventListener
  void on(ViewChange.Notification notification) {
    var asDto = switch (notification.type()) {
      case CHAT -> ViewGql.CHAT;
      case VIEW1 -> ViewGql.VIEW1;
    };

    sink.tryEmitNext(asDto);
  }

}
