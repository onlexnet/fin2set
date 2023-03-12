package dj.services.integration.requistions;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import nordigen.PaginatedRequisitionV2List;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularRequisitionV2;

@FeignClient(value = "requistions", url = "https://ob.nordigen.com")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface RequisitionsClient {

        @GetMapping(value = "/api/v2/requisitions/")
        PaginatedRequisitionV2List getListAllRequisitions(@RequestHeader("Authorization") String accessToken);

        @PostMapping(value = "/api/v2/requisitions/")
        SpectacularRequisitionV2 createRequisition(@RequestHeader("Authorization") String accessToken, @RequestBody RequisitionV2Request requisitionV2Request);

        @GetMapping(value = "/api/v2/requisitions/{requisitionsID}")
        RequisitionV2 getRequisition(@RequestHeader("Authorization") String accessToken, @PathVariable String requisitionsID);
}
