package onlexnet.webapi.gql;

import org.springframework.stereotype.Component;

import onlex.webapi.ViewGql;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Component
public class ReactiveSources {

  private final Many<ViewGql> sink;

  public ReactiveSources() {
    sink = Sinks.many().multicast().<ViewGql>onBackpressureBuffer();
  }

  public void publish(ViewGql view) {
    sink.tryEmitNext(view);
  }

  public Flux<ViewGql> observed() {
    // var inital = Flux.just(ViewGql.CHAT);
    return sink.asFlux().startWith(ViewGql.CHAT);
  }

}
