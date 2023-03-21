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

    UUID accountNumberID;
    int statusCode;

    @Given("set accountNumberID from manually created requisition at sandbox.")
    public void set_account_number_id_from_manually_created_requisition_at_sandbox() {
        accountNumberID = UUID.fromString("7e944232-bda9-40bc-b784-660c7ab5fe78");
    }

    @When("use endpoint account.")
    public void use_endpoint_account() {
        statusCode = accountService.getAccount(accountNumberID).getStatusCodeValue();
    }

    @When("use endpoint transaction.")
    public void use_endpoint_transaction() {
        statusCode = accountService.getTransactions(accountNumberID).getStatusCodeValue();
    }

    @Then("status code should be OK")
    public void status_code_should_be_ok() {
        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.SC_OK);
        statusCode = 0;
    }

}
