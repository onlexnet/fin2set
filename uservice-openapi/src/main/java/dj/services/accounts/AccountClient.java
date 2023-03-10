package dj.services.accounts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import dj.models.NordigenBankStatemant;
import feign.Headers;
import nordigen.AccountV2;

@FeignClient(value = "account", url = "https://ob.nordigen.com")
@Headers({
    "accept: application/json",
    "Content-Type: application/json" })
public interface AccountClient {

    @GetMapping(value = "/api/v2/accounts/{accountID}/")
    AccountV2 getAccount(@RequestHeader("Authorization") String accessToken, @PathVariable String accountID);

    @GetMapping(value = "/api/v2/accounts/{accountID}/transactions/")
    NordigenBankStatemant getTransactions(@RequestHeader("Authorization") String accessToken, @PathVariable String accountID);
    
}
