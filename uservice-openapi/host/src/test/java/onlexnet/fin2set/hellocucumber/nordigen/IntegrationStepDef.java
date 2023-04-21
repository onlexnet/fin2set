package onlexnet.fin2set.hellocucumber.nordigen;

import java.util.Random;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import onlexnet.fin2set.nordigen.generated.Integration;
import onlexnet.fin2set.nordigen.integration.institutions.InstitutionsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IntegrationStepDef {

    @Autowired
    private InstitutionsService integrationService;

    private Integration randomBank;
    private Integration findedBank;

    @Given("get list all Banks from poland and save random Bank.")
    public void get_list_all_Banks_from_poland_and_save_random_Bank() {
        var banks = integrationService.getListBanks("pl");
        
        var rand = new Random();
        randomBank = banks.get(rand.nextInt(banks.size()));
    }

    @When("go find saved object.")
    public void go_find_saved_object() {

        findedBank = integrationService.getBank(randomBank.getId());
    }

    @Then("check if the objects is the same.")
    public void check_if_the_objects_is_the_same() {
        Assertions.assertThat(randomBank).isEqualTo(findedBank);
    }
}
