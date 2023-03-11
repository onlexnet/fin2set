package dj.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dj.models.NordigenBankStatemant;
import dj.services.accounts.AccountService;
import lombok.RequiredArgsConstructor;
import nordigen.AccountV2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountInfoApi {

    private final AccountService accountService;

    @GetMapping("/transactions")
            ResponseEntity<NordigenBankStatemant> getTransactions(@RequestParam String accountID) {
        return ResponseEntity.ok(accountService.getTransactions(accountID));
    }

    @GetMapping("/")
            ResponseEntity<AccountV2> getAccount(@RequestParam String accountID) {
        return ResponseEntity.ok(accountService.getAccount(accountID));
    }
    
}
