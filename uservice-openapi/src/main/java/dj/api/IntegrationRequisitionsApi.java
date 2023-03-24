package dj.api;

import java.util.UUID;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dj.services.integration.requistions.RequisitionsService;
import lombok.RequiredArgsConstructor;
import nordigen.PaginatedRequisitionList;
import nordigen.Requisition;
import nordigen.RequisitionRequest;
import nordigen.SpectacularRequisition;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requisitions")
public class IntegrationRequisitionsApi {

    private final RequisitionsService requisitionsService;

    @GetMapping("/")
    ResponseEntity<PaginatedRequisitionList> getListAllRequisitions() {
        return ResponseEntity.ok(requisitionsService.getListAllRequisitions());
    }

    @PostMapping("/")
    ResponseEntity<SpectacularRequisition> createRequisition(@RequestBody RequisitionRequest RequisitionRequest) {
        return ResponseEntity.ok(requisitionsService.createRequisition(RequisitionRequest));
    }

    @GetMapping("/{requisitionsID}")
    ResponseEntity<Requisition> getRequisition(@PathVariable UUID requisitionsID) {
        return ResponseEntity.ok(requisitionsService.getRequisition(requisitionsID).get());
    }

    @DeleteMapping("/{requisitionsID}")
    ResponseEntity<Response> deleteRequisition(@PathVariable UUID requisitionsID) {
        requisitionsService.deleteRequsition(requisitionsID);
        return ResponseEntity.noContent().build();
    }



}
