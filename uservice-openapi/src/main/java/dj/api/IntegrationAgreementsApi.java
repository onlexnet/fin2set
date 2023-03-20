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
import nordigen.EndUserAgreementRequest;
import nordigen.PaginatedEndUserAgreementList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agreements")
public class IntegrationAgreementsApi {

    private final AgreementsService agreementsService;

    @GetMapping("/")
    ResponseEntity<PaginatedEndUserAgreementList> getListAllAgreements() {
        return ResponseEntity.ok(agreementsService.getListAllAgreements());
    }

    @PostMapping("/")
    ResponseEntity<EndUserAgreement> createAgreement(@RequestBody EndUserAgreementRequest endUserAgreementRequest) {
        return ResponseEntity.ok(agreementsService.createAgreement(endUserAgreementRequest));
    }

    @GetMapping("/{agreementID}")
    ResponseEntity<EndUserAgreement> getAgreement(@PathVariable String agreementID) {
        return ResponseEntity.ok(agreementsService.getAgreement(agreementID).get());
    }

    @DeleteMapping("/{agreementID}")
    ResponseEntity<Response> deleteAgreement(@PathVariable String agreementID) {
        agreementsService.deleteAgreement(agreementID);
        return ResponseEntity.noContent().build();
    }
    
}
