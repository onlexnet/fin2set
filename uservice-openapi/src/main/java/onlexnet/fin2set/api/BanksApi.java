package onlexnet.fin2set.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.nordigen.institutions.InstitutionsService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/institutions")
public class BanksApi {

    private final InstitutionsService institutionsService;
    
    @GetMapping("/banks")
    ResponseEntity<List<Bank>> getListBanks(@RequestParam String country) {
        return ResponseEntity.ok(institutionsService.getListInstitutions(country));
    }

    @GetMapping("/bank")
    ResponseEntity<Bank> getBank(@RequestParam String bankID) {
        return ResponseEntity.ok(institutionsService.getInstitution(bankID));
    }
}
