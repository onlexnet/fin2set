package onlexnet.webapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@LocalTest
class WebapiApplicationTests {
  
  @Test
  void contextLoads() {
  }

}
