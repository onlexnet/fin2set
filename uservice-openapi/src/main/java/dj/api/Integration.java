package dj.api;

import dj.dto.integration.bank.Bank;
import dj.services.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integration")
public class Integration {

    private final IntegrationService integrationService;

    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> getBankList(@RequestParam String country) {
        return ResponseEntity.ok(integrationService.getListBanks(country));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createConnection(@RequestParam String institutionId) {
        return ResponseEntity.ok(integrationService.createConnection(institutionId));
    }

}
