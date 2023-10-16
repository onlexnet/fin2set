package onlexnet.webapi.a;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;

@Controller
public class GreetingsController {
  
  	@SubscriptionMapping
	  public Flux<String> greetings() {
		  return Flux.just("Hello world");
	  }

}
