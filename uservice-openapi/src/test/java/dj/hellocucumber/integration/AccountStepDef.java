package dj.hellocucumber.integration;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import dj.services.integration.accounts.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountStepDef {

    @Autowired
    private AccountService accountService;

    private UUID accountNumberID;
    private UUID accountFoundID;
    private int statusCode;

    @Given("set accountNumberID from manually created requisition at sandbox.")
    public void set_account_number_id_from_manually_created_requisition_at_sandbox() {
        accountNumberID = UUID.fromString("7e944232-bda9-40bc-b784-660c7ab5fe78");
    }

    @When("use endpoint account.")
    public void use_endpoint_account() {
        accountFoundID = accountService.getAccount(accountNumberID).getId();
    }

    @When("use endpoint transaction.")
    public void use_endpoint_transaction() {
        statusCode = accountService.getTransactions(accountNumberID).getStatusCodeValue();
    }

    @Then("the ID of the found object should match the set one.")
    public void the_id_of_the_found_object_should_match_the_set_one() {
        Assertions.assertThat(accountFoundID).isEqualTo(accountNumberID);
    }

    @Then("status code should be OK.")
    public void status_code_should_be_ok() {
        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.SC_OK);
        statusCode = 0;
    }

}
