package onlexnet.webapi.infra;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.ApplicationModuleTest.BootstrapMode;

import onlexnet.webapi.LocalTest;

@ApplicationModuleTest(mode = BootstrapMode.ALL_DEPENDENCIES)
@LocalTest
public class InfraTests {
  
  @Test
  void contextInit() {
  }

}
