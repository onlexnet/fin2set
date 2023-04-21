package onlexnet.fin2set.hellocucumber.nordigen;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.accounts.AccountService;

public class AccountStepDef {

    @Autowired
    private AccountService accountService;

    private UUID accountNumberID;
    private UUID accountFoundID;
    private Optional<BankStatement> bankStatemant;

    /***
     * 7e944232-bda9-40bc-b784-660c7ab5fe78 
     * It is a hardcoded account number from sanbox nordigen, using it requires a standard 
     * connection procedure. You need to use the jwt token generated from our secret id and 
     * secret key, perform a post requisition, go to the page with the link and click the 
     * release of consents to be able to use it.
     */
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
        bankStatemant = Optional.of(accountService.getTransactions(accountNumberID));
    }

    @Then("the ID of the found object should match the set one.")
    public void the_id_of_the_found_object_should_match_the_set_one() {
        Assertions.assertThat(accountFoundID).isEqualTo(accountNumberID);
    }

    @Then("status code should be OK.")
    public void status_code_should_be_ok() {
        Assertions.assertThat(bankStatemant).isNotEmpty();
    }

}
