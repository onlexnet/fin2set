package dj.services.integration.requistions;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@FeignClient(value = "requistions", url = "https://ob.nordigen.com/api/v2/requisitions")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface RequisitionsClient {

        @GetMapping(value = "/")
        PaginatedRequisitionV2List getListAllRequisitions(@RequestHeader("Authorization") String accessToken);

        @PostMapping(value = "/")
        SpectacularRequisitionV2 createRequisition(@RequestHeader("Authorization") String accessToken, @RequestBody RequisitionV2Request requisitionV2Request);

        @GetMapping(value = "/{requisitionsID}")
        RequisitionV2 getRequisition(@RequestHeader("Authorization") String accessToken, @PathVariable String requisitionsID);

        @DeleteMapping(value = "/{requisitionsID}")
        void deleteRequsition(@RequestHeader("Authorization") String accessToken, @PathVariable String requisitionsID);
}
