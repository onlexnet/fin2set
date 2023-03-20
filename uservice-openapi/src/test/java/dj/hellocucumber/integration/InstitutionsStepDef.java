package dj.hellocucumber.integration;

import java.util.Random;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import dj.services.integration.institutions.InstitutionsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nordigen.Integration;

public class InstitutionsStepDef {

    @Autowired
    private InstitutionsService institutionsService;

    private Integration randomInstitution;
    private Integration findedInstitution;

    @Given("get list all institutions from poland and save random institution.")
    public void get_list_all_institutions_from_poland_and_save_random_institution() {
        var institutions = institutionsService.getListInstitutions("pl");
        
        var rand = new Random();
        randomInstitution = institutions.get(rand.nextInt(institutions.size()));
    }

    @When("go find saved object.")
    public void go_find_saved_object() {

        findedInstitution = institutionsService.getInstitution(randomInstitution.getId());
    }

    @Then("check if the objects is the same.")
    public void check_if_the_objects_is_the_same() {
        Assertions.assertThat(randomInstitution).isEqualTo(findedInstitution);
    }
}
