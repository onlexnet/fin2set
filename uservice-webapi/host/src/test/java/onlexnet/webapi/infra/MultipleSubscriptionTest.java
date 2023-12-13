package onlexnet.webapi.infra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;

import onlex.webapi.ViewGql;
import onlexnet.webapi.AppApi;
import onlexnet.webapi.DaprExtension;

@SpringBootTest
@ExtendWith(DaprExtension.class)
public class MultipleSubscriptionTest {

  @Autowired
  ExecutionGraphQlService executionGraphQlService;

  @Test
  void contextLoads() {
    var api1 = new AppApi("/graphql", "user1@example.com", executionGraphQlService, new AppApi.NoOpApiCallbacks());
    var api2 = new AppApi("/graphql", "user1@example.com", executionGraphQlService, new AppApi.NoOpApiCallbacks());

    var view1 = api1.view().blockFirst();
    var view2 = api2.view().blockFirst();

    Assertions.assertThat(view1).isEqualTo(ViewGql.CHAT);
    Assertions.assertThat(view2).isEqualTo(ViewGql.CHAT);
  }

}
