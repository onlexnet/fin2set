package dj.services.agreements;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;

@FeignClient(value = "agreement", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface AgreementsClient {

    @PostMapping(value = "/api/v2/agreements/enduser/")
    EndUserAgreement createAgreement(@RequestHeader("Authorization") String accessToken,
                                  @RequestBody EndUserAgreementRequest endUserAgreementRequest);
    
}
