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

import dj.models.dto.PaginatedRequisitionListDTO;
import dj.models.dto.RequisitionDTO;
import dj.models.dto.SpectacularRequisitionDTO;
import dj.services.integration.requistions.RequisitionsService;
import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requisitions")
public class IntegrationRequisitionsApi {

    private final RequisitionsService requisitionsService;

    @GetMapping("/")
    ResponseEntity<PaginatedRequisitionListDTO> getListAllRequisitions() {
        return ResponseEntity.ok(requisitionsService.getListAllRequisitions());
    }

    @PostMapping("/")
    ResponseEntity<SpectacularRequisitionDTO> createRequisition(@RequestBody RequisitionRequest RequisitionRequest) {
        return ResponseEntity.ok(requisitionsService.createRequisition(RequisitionRequest));
    }

    @GetMapping("/{requisitionsID}")
    ResponseEntity<RequisitionDTO> getRequisition(@PathVariable UUID requisitionsID) {
        return requisitionsService.getRequisition(requisitionsID)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{requisitionsID}")
    ResponseEntity<Response> deleteRequisition(@PathVariable UUID requisitionsID) {
        requisitionsService.deleteRequsition(requisitionsID);
        return ResponseEntity.noContent().build();
    }



}
