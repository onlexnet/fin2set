package onlexnet.fin2set.api;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.bankstatement.OnlexBankStatementService;
import onlexnet.fin2set.domain.models.OnlexBankStatement;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onlex")
class OnlexBankApi {

    private final OnlexBankStatementService onlexService;

    @GetMapping("/bankstatement")
    ResponseEntity<OnlexBankStatement> getTransactions(@RequestParam UUID accountID) {
        return ResponseEntity.ok(onlexService.getOnlexBankStatement(accountID));
    }

}
