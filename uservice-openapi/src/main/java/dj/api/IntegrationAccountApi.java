package dj.api;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dj.models.NordigenBankStatemant;
import dj.services.integration.accounts.AccountService;
import lombok.RequiredArgsConstructor;
import nordigen.AccountV2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class IntegrationAccountApi {

    private final AccountService accountService;

    @GetMapping("/transactions")
            ResponseEntity<NordigenBankStatemant> getTransactions(@RequestParam UUID accountNumberID) {
        return accountService.getTransactions(accountNumberID);
    }

    @GetMapping("/info")
            ResponseEntity<AccountV2> getAccount(@RequestParam UUID accountNumberID) {
        return accountService.getAccount(accountNumberID);
    }
    
}
