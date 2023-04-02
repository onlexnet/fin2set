package dj.hellocucumber.integration;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import dj.services.integration.agreements.AgreementsService;
import dj.services.integration.requistions.RequisitionsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nordigen.EndUserAgreementRequest;
import nordigen.RequisitionRequest;

public class RequisitionStepDef {

    @Autowired
    private AgreementsService agreementsService;

    @Autowired
    private RequisitionsService requisitionsService;

    private UUID createdAgreementID;

    private UUID createdRequisitionID;
    private UUID foundRequisitionID;

    @Given("create new agreement for requisition.")
    public void create_new_agreement_for_requisition() {

        var endUserAgreementRequest = new EndUserAgreementRequest()
                .institutionId("REVOLUT_REVOGB21")
                .maxHistoricalDays(90)
                .accessValidForDays(30)
                .accessScope(List.of("balances", "details", "transactions"));

        createdAgreementID = agreementsService.createAgreement(endUserAgreementRequest).getId();
    }

    @Given("create new requisition.")
    public void create_new_requisition() {

        var requisitionRequest = new RequisitionRequest()
                .redirect(URI.create("http://localhost:8080/api/integration/info"))
                .institutionId("REVOLUT_REVOGB21")
                .reference(UUID.randomUUID().toString())
                .agreement(createdAgreementID)
                .userLanguage("PL")

                // Optional parameters
                .ssn("")
                .accountSelection(false)
                .redirectImmediate(false);

        createdRequisitionID = requisitionsService.createRequisition(requisitionRequest).getId();
    }

    @When("looking created requisition based at id.")
    public void looking_created_requisition_based_at_id() {
        foundRequisitionID = requisitionsService.getRequisition(createdRequisitionID).get().getId();
    }

    @When("check if created requisition have the same id as found.")
    public void check_if_created_requisition_have_the_same_id_as_found() {
        Assertions.assertThat(createdRequisitionID).isEqualTo(foundRequisitionID);
    }

    @When("delete created requisition.")
    public void delete_created_requisition() {
        requisitionsService.deleteRequsition(createdRequisitionID);
    }

    @Then("looking deleted requisition.")
    public void looking_deleted_requisition() {
        var maybeResponse = requisitionsService.getRequisition(createdRequisitionID);
        Assertions.assertThat(maybeResponse).isEmpty();

    }

    @Then("looking if agreement was deleted with requisition.")
    public void looking_if_agreement_was_deleted_with_requisition() {
        var maybeResponse = agreementsService.getAgreement(createdAgreementID);
        Assertions.assertThat(maybeResponse).isEmpty();
    }

}
