package dj.hellocucumber.integration;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import dj.services.integration.agreements.AgreementsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;

public class AgreementsStepDef {

    @Autowired
    private AgreementsService agreementsService;

    private EndUserAgreement createdAgreement;
    private EndUserAgreement getAgreement;

    @Given("create new agreement from example.")
    public void create_new_agreement_from_example() {

        var endUserAgreementRequest = new EndUserAgreementRequest()
                .institutionId("REVOLUT_REVOGB21")
                .maxHistoricalDays(90)
                .accessValidForDays(30)
                .addAccessScopeItem("balances")
                .addAccessScopeItem("details");

        createdAgreement = agreementsService.createAgreement(endUserAgreementRequest);
    }

    @When("get created agreement.")
    public void get_created_agreement() {
        getAgreement = agreementsService.getAgreement(createdAgreement.getId().toString());
    }

    @Then("check if the agreements is the same.")
    public void check_if_the_agreements_is_the_same() {
        Assertions.assertThat(createdAgreement.getId()).isEqualTo(getAgreement.getId());
    }

    @Then("delete agreement.")
    public void delete_agreement() {
        agreementsService.deleteAgreement(createdAgreement.getId().toString());

        

    }
}