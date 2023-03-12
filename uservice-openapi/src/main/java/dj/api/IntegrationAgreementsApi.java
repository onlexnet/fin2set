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

import dj.services.integration.agreements.AgreementsService;
import lombok.RequiredArgsConstructor;
import nordigen.EndUserAgreement;
import nordigen.PaginatedEndUserAgreementList;
import nordigen.PaginatedRequisitionV2List;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularRequisitionV2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agreements")
public class IntegrationAgreementsApi {

    private final AgreementsService agreementsService;

    @GetMapping("/")
    ResponseEntity<PaginatedEndUserAgreementList> getListAllRequisitions() {
        return ResponseEntity.ok(agreementsService.getListAllAgreements());
    }

    @PostMapping("/")
    ResponseEntity<SpectacularRequisitionV2> createRequisition(@RequestBody RequisitionV2Request requisitionV2Request) {
        return ResponseEntity.ok(agreementsService.createAgreement(null));
    }

    @GetMapping("/{requisitionsID}")
    ResponseEntity<EndUserAgreement> getRequisition(@PathVariable String requisitionsID) {
        return ResponseEntity.ok(agreementsService.getAgreement(requisitionsID));
    }

    @DeleteMapping("/{requisitionsID}")
    ResponseEntity<Response> deleteRequisition(@PathVariable String requisitionsID) {
        agreementsService.deleteAgreement(requisitionsID);
        return ResponseEntity.noContent().build();
    }
    
}
