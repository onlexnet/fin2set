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

import dj.models.dto.EndUserAgreementDTO;
import dj.models.dto.PaginatedEndUserAgreementListDTO;
import dj.services.integration.agreements.AgreementsService;
import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agreements")
public class IntegrationAgreementsApi {

    private final AgreementsService agreementsService;

    @GetMapping("/")
    ResponseEntity<PaginatedEndUserAgreementListDTO> getListAllAgreements() {
        return ResponseEntity.ok(agreementsService.getListAllAgreements());
    }

    @PostMapping("/")
    ResponseEntity<EndUserAgreementDTO> createAgreement(@RequestBody EndUserAgreementRequest endUserAgreementRequest) {
        return ResponseEntity.ok(agreementsService.createAgreement(endUserAgreementRequest));
    }

    @GetMapping("/{agreementID}")
    ResponseEntity<EndUserAgreementDTO> getAgreement(@PathVariable UUID agreementID) {
        return ResponseEntity.ok(agreementsService.getAgreement(agreementID).orElse(null));
    }

    @DeleteMapping("/{agreementID}")
    ResponseEntity<Response> deleteAgreement(@PathVariable UUID agreementID) {
        agreementsService.deleteAgreement(agreementID);
        return ResponseEntity.noContent().build();
    }

}
