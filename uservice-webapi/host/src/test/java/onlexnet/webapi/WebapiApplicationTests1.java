package onlexnet.webapi;

import java.time.Duration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;

@SpringBootTest
@ExtendWith(DaprExtension.class)
class WebapiApplicationTests1 {

  @Autowired
  ExecutionGraphQlService executionGraphQlService;

  @Test
  void contextLoads() {
  }

}
