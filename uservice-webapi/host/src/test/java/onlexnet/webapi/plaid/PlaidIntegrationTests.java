package onlexnet.webapi.plaid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.LocalTest;
import onlexnet.webapi.plaid.PlaidConnection;

@ApplicationModuleTest
@LocalTest
@RequiredArgsConstructor
public class PlaidIntegrationTests {

  @Autowired
  PlaidConnection plaidConnection;

  @Test
  void myTest(Scenario scenario) {
  }

}
