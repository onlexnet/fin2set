package dj.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dj.dto.integration.AgreementData;
import dj.dto.integration.IntegrationForm;
import dj.dto.integration.bank.Bank;
import dj.dto.integration.build_a_link.DataForCreateConnection;
import dj.dto.integration.build_a_link.ResponseEndingIntegration;
import feign.Headers;

@FeignClient(value = "integration", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface IntegrationClient {

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


