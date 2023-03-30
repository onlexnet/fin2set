package dj.services.integration.requistions;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import nordigen.PaginatedRequisitionList;
import nordigen.Requisition;
import nordigen.RequisitionRequest;
import nordigen.SpectacularRequisition;

@FeignClient(value = "requistions", url = "https://ob.nordigen.com/api/v2/requisitions")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface RequisitionsClient {

        @GetMapping(value = "/")
        PaginatedRequisitionList getListAllRequisitions(@RequestHeader("Authorization") String accessToken);

        @PostMapping(value = "/")
        SpectacularRequisition createRequisition(@RequestHeader("Authorization") String accessToken, @RequestBody RequisitionRequest requisitionRequest);

        @GetMapping(value = "/{requisitionsID}")
        Requisition getRequisition(@RequestHeader("Authorization") String accessToken, @PathVariable UUID requisitionsID);

        @DeleteMapping(value = "/{requisitionsID}")
        void deleteRequsition(@RequestHeader("Authorization") String accessToken, @PathVariable UUID requisitionsID);
}
