package onlexnet.fin2set.hellocucumber.nordigen;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onlexnet.fin2set.nordigen.generated.EndUserAgreement;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;
import onlexnet.fin2set.nordigen.integration.agreements.AgreementsService;

public class AgreementsStepDef {

    @Autowired
    private AgreementsService agreementsService;

    private EndUserAgreement createdAgreement;
    private EndUserAgreement getAgreement;

    @Given("create new agreement.")
    public void create_new_agreement_from_example() {

        var endUserAgreementRequest = new EndUserAgreementRequest("REVOLUT_REVOGB21")
                .maxHistoricalDays(90)
                .accessValidForDays(30)
                .accessScope(List.of("balances", "details", "transactions"));  

        createdAgreement = agreementsService.createAgreement(endUserAgreementRequest);
    }

    @When("get created agreement.")
    public void get_created_agreement() {
        getAgreement = agreementsService.getAgreement(createdAgreement.getId()).get();
    }

    @Then("check if the agreements is the same.")
    public void check_if_the_agreements_is_the_same() {
        Assertions.assertThat(createdAgreement).isEqualTo(getAgreement);
    }

    @Then("delete agreement.")
    public void delete_agreement() {
        agreementsService.deleteAgreement(createdAgreement.getId());

        var maybeResponse = agreementsService.getAgreement(createdAgreement.getId());
        
        Assertions.assertThat(maybeResponse).isEmpty();
    }
}
