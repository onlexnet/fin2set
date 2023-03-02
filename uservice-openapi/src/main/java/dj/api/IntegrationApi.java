package dj.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dj.services.IntegrationService;
import dj.services.institutions.InstitututionsService;
import lombok.RequiredArgsConstructor;
import nordigen.Integration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integration")
public class IntegrationApi {

    private final IntegrationService integrationService;
    private final InstitututionsService institututionsService;

    @GetMapping("/banks")
    public ResponseEntity<List<Integration>> getBankList(@RequestParam String country) {
        return ResponseEntity.ok(institututionsService.getListBanks(country));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createConnection(@RequestParam String institutionId) {
        return ResponseEntity.ok(integrationService.createConnection(institutionId));
    }

}
