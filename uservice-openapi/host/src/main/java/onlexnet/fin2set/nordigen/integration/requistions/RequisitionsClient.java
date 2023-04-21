package onlexnet.fin2set.nordigen.integration.requistions;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import onlexnet.fin2set.nordigen.generated.PaginatedRequisitionList;
import onlexnet.fin2set.nordigen.generated.Requisition;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;
import onlexnet.fin2set.nordigen.generated.SpectacularRequisition;

@FeignClient(value = "requistions", url = "https://ob.nordigen.com/api/v2/requisitions")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface RequisitionsClient {

        @GetMapping(value = "/")
        ResponseEntity<PaginatedRequisitionList> getListAllRequisitions(
                        @RequestHeader("Authorization") String accessToken);

        @PostMapping(value = "/")
        ResponseEntity<SpectacularRequisition> createRequisition(
                        @RequestHeader("Authorization") String accessToken,
                        @RequestBody RequisitionRequest requisitionRequest);

        @GetMapping(value = "/{requisitionsID}")
        ResponseEntity<Requisition> getRequisition(
                        @RequestHeader("Authorization") String accessToken,
                        @PathVariable UUID requisitionsID);

        @DeleteMapping(value = "/{requisitionsID}")
        ResponseEntity<Object> deleteRequsition(
                        @RequestHeader("Authorization") String accessToken,
                        @PathVariable UUID requisitionsID);
}
