package dj.api;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dj.models.OnlexBankStatement;
import dj.services.onlex.bankstatement.OnlexBankStatementService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onlex")
public class OnlexBankApi {

    private final OnlexBankStatementService onlexService;

    @GetMapping("/bankstatement")
    ResponseEntity<OnlexBankStatement> createConection(@RequestParam UUID accountID) {
        return ResponseEntity.ok(onlexService.getOnlexBankStatement(accountID));
    }

}
