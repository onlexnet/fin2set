package dj.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dj.services.institutions.InstitutionsService;
import dj.services.requistions.RequisitionsService;
import dj.services.requistions.RequistionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nordigen.Integration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integration")
@Slf4j
public class IntegrationApi {

    private final InstitutionsService institututionsService;
    private final RequisitionsService requisitionsService;

    @GetMapping("/banks")
    ResponseEntity<List<Integration>> getBankList(@RequestParam String country) {
        return ResponseEntity.ok(institututionsService.getListBanks(country));
    }

    @GetMapping("/login")
    ResponseEntity<URI> createConnection(@RequestParam String institutionId) {
        return ResponseEntity.status(HttpStatus.FOUND).location(requisitionsService.createConnection(institutionId))
                .build();
    }

    @GetMapping("/move")
    ResponseEntity<String> getListAccounts(@RequestParam(name = "ref") String reference) {
        log.info("sparta!");
        return ResponseEntity.ok(reference);
    }

}
