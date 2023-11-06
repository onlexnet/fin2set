package onlexnet.webapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "DAPR_GRPC_PORT=0" })
class WebapiApplicationTests {

  
  @Test
  void contextLoads() {
  }

}
