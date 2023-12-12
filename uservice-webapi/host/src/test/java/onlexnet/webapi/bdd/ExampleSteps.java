package onlexnet.webapi.bdd;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.When;
import onlex.webapi.ViewGql;
import onlexnet.webapi.domain.ValName;

public class ExampleSteps {

  @Autowired
  Api api;

  @When("{userAlias} current view is '{viewName}'")
  public void user_has_view(ValName userAlias, ViewGql view) {
    var facts = api.get(userAlias);
    Assertions.assertThat(facts.currentView).isEqualTo(view);
  }

  @When("user {userAlias} said {string}")
  public void user_said(ValName userAlias, String text) {
    api.act(userAlias).say(text);
  }
}
