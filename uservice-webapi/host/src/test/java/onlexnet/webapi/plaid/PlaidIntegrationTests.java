package onlexnet.webapi.plaid;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.LocalTest;

@ApplicationModuleTest
@LocalTest
@RequiredArgsConstructor
@Disabled
public class PlaidIntegrationTests {

  @Autowired
  PlaidConnection plaidConnection;

  @Test
  void myTest(Scenario scenario) {

    plaidConnection.doSomething();
  }

}
