package onlexnet.webapi.bdd;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onlex.webapi.ViewGql;
import onlexnet.webapi.domain.models.ValName;
import onlexnet.webapi.openai.OpenAi;

public class ExampleSteps {

  @Autowired
  Api api;

  @Autowired
  OpenAi openAi;

  @When("{userAlias} current view is '{viewName}'")
  public void user_has_view(ValName userAlias, ViewGql view) {
    var facts = api.get(userAlias);
    Assertions.assertThat(facts.currentView).isEqualTo(view);
  }

  @When("user {userAlias} said {string}")
  public void user_said(ValName userAlias, String text) {
    api.act(userAlias).say(text);
  }

  @Then("last {userAlias} response is {string}")
  public void last_response_is(ValName userAlias, String expected) {
    var facts = api.get(userAlias);
    var actual = facts.lastResponse;

    var actualAsVectors = openAi.getEmbedings(actual);
    var expectedAsVectors = openAi.getEmbedings(expected);

    var similarity = Similarity.cosine(actualAsVectors, expectedAsVectors);
    Assertions.assertThat(similarity)
      .as("Actual vs Expected:\n[%s]\n <> \n[%s]", actual, expected)
      .isGreaterThan(0.85);
  }
}
