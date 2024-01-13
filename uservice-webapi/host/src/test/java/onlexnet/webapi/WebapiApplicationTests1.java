package onlexnet.webapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;

@SpringBootTest
@LocalTest
class WebapiApplicationTests1 {

  @Autowired
  ExecutionGraphQlService executionGraphQlService;

  @Test
  void contextLoads() {
  }

}
