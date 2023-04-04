package dj.hellocucumber.integration;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import onlexnet.fin2set.nordigen.token.TokenService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TokenStepDef {

    @Autowired
    private TokenService tokenService;

    private String accessToken;
    private String refreshedAccessToken;

    @Given("get access token.")
    public void get_access_token() {
         accessToken = tokenService.getTokens().getAccess();
    }
    
    @When("use refresh token to get new access token, and set refreshed access token.")
    public void use_refresh_token_to_get_new_access_token_and_set_refreshed_access_token() {
        refreshedAccessToken = tokenService.refreshAccessToken().getAccess();
    }

    @Then("first access token will be not equals refreshed token.")
    public void first_access_token_will_be_not_equals_refreshed_token() {
        Assertions.assertThat(accessToken).isNotEqualTo(refreshedAccessToken);
    }

    

}
