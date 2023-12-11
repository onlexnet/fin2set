package onlexnet.webapi.bdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.ExecutionGraphQlService;

import io.cucumber.java.en.*;
import onlexnet.webapi.AppApi;

public class ExampleSteps {

  @Autowired
  ExecutionGraphQlService executionGraphQlService;

  //   var api = new AppApi("graphiql", "slawomir@siudek.net", executionGraphQlService);
  @Given("an example scenario")
  public void anExampleScenario() {
    Assertions.assertThat(executionGraphQlService).isNotNull();
  }

  @When("all step definitions are implemented")
  public void allStepDefinitionsAreImplemented() {
  }

  @Then("the scenario passes")
  public void theScenarioPasses() {
  }

}
