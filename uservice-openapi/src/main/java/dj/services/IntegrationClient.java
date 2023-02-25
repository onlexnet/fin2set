package dj.services;

import dj.dto.integration.AgreementData;
import dj.dto.integration.IntegrationForm;
import dj.dto.integration.bank.Bank;
import dj.dto.integration.build_a_link.DataForCreateConnection;
import dj.dto.integration.build_a_link.ResponseEndingIntegration;
import dj.dto.integration.secrets.Secrets;
import dj.dto.integration.secrets.token.Tokens;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "integration", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface IntegrationClient {

    @PostMapping(value = "/api/v2/token/new/")
    Tokens createTokens(@RequestBody Secrets secrets);

    @GetMapping(value = "/api/v2/institutions/?country={country}")
    List<Bank> getBankList(@RequestHeader("Authorization") String accessToken,
                           @PathVariable("country") String country);

    @PostMapping(value = "/api/v2/agreements/enduser/")
    AgreementData createAgreement(@RequestHeader("Authorization") String accessToken,
                                  @RequestBody IntegrationForm integrationForm);

    @PostMapping(value = "/api/v2/requisitions/")
    ResponseEndingIntegration createConnection(@RequestHeader("Authorization") String accessToken,
                                               @RequestBody DataForCreateConnection dataForCreateConnection);
}


