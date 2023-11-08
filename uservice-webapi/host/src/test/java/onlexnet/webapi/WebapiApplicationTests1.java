package onlexnet.webapi;

import java.time.Duration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;

import onlex.webapi.ViewGql;

@SpringBootTest
@ExtendWith(DaprExtension.class)
class WebapiApplicationTests1 {

  @Autowired
  ExecutionGraphQlService executionGraphQlService;

  @Test
  void contextLoads() {

    var api = new AppApi("graphiql", "slawomir@siudek.net", executionGraphQlService);

    var data = api.view().blockFirst(Duration.ofSeconds(1));

    Assertions.assertThat(data).isEqualTo(ViewGql.CHAT);

    // WebGraphQlHandler handler = null;
    
    // WebGraphQlTester tester = WebGraphQlTester.builder(handler)
		// .headers(headers -> headers.setBasicAuth("joe", "..."))
		// .build();
    
    // StepVerifier.create(greetingFlux)
    //     .expectNext("Hi")
    //     .expectNext("Bonjour")
    //     .ex`pectNext("Hola")
    //     .verifyComplete();

  }

}
