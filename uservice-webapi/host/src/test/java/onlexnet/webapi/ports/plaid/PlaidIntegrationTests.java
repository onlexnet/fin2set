package onlexnet.webapi.ports.plaid;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.ApplicationModuleTest.BootstrapMode;
import org.springframework.modulith.test.Scenario;

import onlexnet.webapi.LocalTest;

@ApplicationModuleTest(mode = BootstrapMode.ALL_DEPENDENCIES)
@LocalTest
public class PlaidIntegrationTests {

  // @Autowired
  // PlaidConnection connection;

  // @Container
  // @ServiceConnection
  // static MSSQLServerContainer<?> mssqlserver = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server")
  //     .withUrlParam("trustServerCertificate","true")
  //     .acceptLicense();
  
  @Test
  void myTest(Scenario scenario) {
    // assertThat(connection.isWorking()).isFalse();
  }

}
