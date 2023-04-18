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
import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;
import onlexnet.fin2set.nordigen.NordigenPort;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integration")
class OnlexConnectApi {

    private final NordigenPort nordigenPort;
    
    
    @GetMapping("/banks")
    ResponseEntity<List<Bank>> getListBanks(@RequestParam String country) {
        return ResponseEntity.ok(nordigenPort.getListBanks(country));
    }

    @GetMapping("/bank")
    ResponseEntity<Bank> getBank(@RequestParam String bankID) {
        return ResponseEntity.ok(nordigenPort.getBank(bankID));
    }

    @GetMapping("/bankstatement")
    ResponseEntity<BankStatement> getTransactions(@RequestParam UUID accountID) {
        return ResponseEntity.ok(nordigenPort.getBankStatement(accountID));
    }

    @GetMapping("/connection")
    ResponseEntity<URI> createConection(@RequestParam String bankId) {
        return ResponseEntity.status(HttpStatus.FOUND).location(nordigenPort.createLinkToConnect(bankId))
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
    ResponseEntity<BankUserDetailsConnection> getInfoAboutConection(@RequestParam(name = "ref") String reference) {
        return ResponseEntity.ok(nordigenPort.getInfoAboutConection(reference));
    }

}
