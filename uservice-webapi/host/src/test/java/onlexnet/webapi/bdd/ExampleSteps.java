package onlexnet.webapi.bdd;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onlex.webapi.ViewGql;
import onlexnet.webapi.domain.ValName;

public class ExampleSteps {

  @Autowired
  Api api;

  @When("user {userAlias} said {string}")
  public void user_said(ValName userAlias, String text) {
    var view = api.getTick(userAlias);
    Assertions.assertThat(view).isEqualTo(ViewGql.CHAT);
  }

  @Then("webapi sends logout event")
  public void webapi_sends_logout_event() {
      // Write code here that turns the phrase above into concrete actions
      // throw new io.cucumber.java.PendingException();
  }
}
