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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nordigen.Integration;
import nordigen.RequisitionV2;

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
    ResponseEntity<URI> createRequisition(@RequestParam String institutionId) {
        return ResponseEntity.status(HttpStatus.FOUND).location(requisitionsService.createRequisition(institutionId))
                .build();
    }

    /**
     * Endpoint under which nordigen sends us the client after the authorization on the bank's side has been completed
     * 
     * Nordigen adds the ref parameter, which is the UUID reference generated
     * by us, and we assign it to the received id in order to replace them and
     * execute a query that will return a complete order document
     */
    @GetMapping("/accounts")
            ResponseEntity<RequisitionV2> getListAccounts(@RequestParam(name = "ref") String reference) {
        return ResponseEntity.ok(requisitionsService.getListAccounts(reference));
    }

}
