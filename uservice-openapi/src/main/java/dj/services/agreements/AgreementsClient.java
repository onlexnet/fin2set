package dj.services.agreements;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dj.dto.integration.AgreementData;
import dj.dto.integration.IntegrationForm;
import feign.Headers;

@FeignClient(value = "agreement", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface AgreementsClient {

    @PostMapping(value = "/api/v2/agreements/enduser/")
    AgreementData createAgreement(@RequestHeader("Authorization") String accessToken,
                                  @RequestBody IntegrationForm integrationForm);
    
}
