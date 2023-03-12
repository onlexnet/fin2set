package dj.api;

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
import nordigen.PaginatedRequisitionV2List;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularRequisitionV2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requisitions")
public class RequisitionsApi {

    private final RequisitionsService requisitionsService;

    @GetMapping("/")
    ResponseEntity<PaginatedRequisitionV2List> getListAllRequisitions() {
        return ResponseEntity.ok(requisitionsService.getListAllRequisitions());
    }

    @PostMapping("/")
    ResponseEntity<SpectacularRequisitionV2> createRequisition(@RequestBody RequisitionV2Request requisitionV2Request) {
        return ResponseEntity.ok(requisitionsService.createRequisition(requisitionV2Request));
    }

    @GetMapping("/{requisitionsID}")
    ResponseEntity<RequisitionV2> getRequisition(@PathVariable String requisitionsID) {
        return ResponseEntity.ok(requisitionsService.getRequisition(requisitionsID));
    }

    @DeleteMapping("/{requisitionsID}")
    ResponseEntity<Response> deleteRequisition(@PathVariable String requisitionsID) {
        requisitionsService.deleteRequsition(requisitionsID);
        return ResponseEntity.noContent().build();
    }



}
