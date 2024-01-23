package onlexnet.webapi.plaid;

import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;
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
public class PlaidIntegrationTests {

  @Autowired
  PlaidService plaidConnection;

  @Test
  void myTest(Scenario scenario) {

    var linkToken = plaidConnection.createLinkToken();
    Assertions
      .assertThat(linkToken)
      .startsWith("link-sandbox-");
  }

}
