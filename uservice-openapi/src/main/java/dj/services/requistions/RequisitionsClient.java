package dj.services.requistions;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularRequisitionV2;

@FeignClient(value = "requistions", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface RequisitionsClient {

    @PostMapping(value = "/api/v2/requisitions/")
    SpectacularRequisitionV2 createConnection(@RequestHeader("Authorization") String accessToken,
                                               @RequestBody RequisitionV2Request requisitionV2Request);
    
}
