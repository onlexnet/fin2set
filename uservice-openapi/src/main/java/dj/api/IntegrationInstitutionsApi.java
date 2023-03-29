package dj.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dj.models.dto.IntegrationDTO;
import dj.services.integration.institutions.InstitutionsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/institutions")
public class IntegrationInstitutionsApi {

    private final InstitutionsService institutionsService;
    
    @GetMapping("/banks")
    ResponseEntity<List<IntegrationDTO>> getListInstitutions(@RequestParam String country) {
        return ResponseEntity.ok(institutionsService.getListInstitutions(country));
    }

    @GetMapping("/bank")
    ResponseEntity<IntegrationDTO> getInstitution(@RequestParam String institutionID) {
        return ResponseEntity.ok(institutionsService.getInstitution(institutionID));
    }
}
