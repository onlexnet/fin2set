package onlexnet.fin2set.api;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.bankstatement.BankStatementService;
import onlexnet.fin2set.domain.connect.ConnectService;
import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.domain.models.CustomerData;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.nordigen.institutions.InstitutionsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integration")
public class OnlexConnectApi {

    private final ConnectService connectService;
    private final BankStatementService onlexService;
    private final InstitutionsService institutionsService;
    
    @GetMapping("/banks")
    ResponseEntity<List<Bank>> getListBanks(@RequestParam String country) {
        return ResponseEntity.ok(institutionsService.getListInstitutions(country));
    }

    @GetMapping("/bank")
    ResponseEntity<Bank> getBank(@RequestParam String bankID) {
        return ResponseEntity.ok(institutionsService.getInstitution(bankID));
    }

    @GetMapping("/bankstatement")
    ResponseEntity<BankStatement> getTransactions(@RequestParam UUID accountID) {
        return ResponseEntity.ok(onlexService.getBankStatement(accountID));
    }

    @GetMapping("/connection")
    ResponseEntity<URI> createConection(@RequestParam String institutionId) {
        return ResponseEntity.status(HttpStatus.FOUND).location(connectService.createLinkToConnect(institutionId))
                .build();
    }

    /**
     * Endpoint under which nordigen sends us the client after the authorization on
     * the bank's side has been completed
     * 
     * Nordigen adds the ref parameter, which is the UUID reference generated
     * by us, and we assign it to the received id in order to replace them and
     * execute a query that will return a complete order document
     */
    @GetMapping("/info")
    ResponseEntity<CustomerData> getInfoAboutConection(@RequestParam(name = "ref") String reference) {
        return ResponseEntity.ok(connectService.getInfoAboutConection(reference));
    }

}
