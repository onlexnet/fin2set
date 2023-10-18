package onlexnet.webapi.gql;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import onlex.webapi.ViewGql;
import reactor.core.publisher.Flux;

@Controller
public class GreetingsController {
  
  	@SubscriptionMapping
	  public Flux<ViewGql> view() {
		  return Flux.just(ViewGql.CHAT);
	  }

}
