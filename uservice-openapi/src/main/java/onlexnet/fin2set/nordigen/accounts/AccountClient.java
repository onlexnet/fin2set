package onlexnet.fin2set.nordigen.accounts;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import onlexnet.fin2set.api.dto.NordigenBankStatemant;
import onlexnet.fin2set.nordigen.generated.Account;


@FeignClient(value = "account", url = "https://ob.nordigen.com", decode404=true)
@Headers({
    "accept: application/json",
    "Content-Type: application/json" })
public interface AccountClient {

    @GetMapping(value = "/api/v2/accounts/{accountNumberID}/")
    ResponseEntity<Account> getAccount(@RequestHeader("Authorization") String accessToken, @PathVariable UUID accountNumberID);

    @GetMapping(value = "/api/v2/accounts/{accountNumberID}/transactions/")
    ResponseEntity<NordigenBankStatemant> getTransactions(@RequestHeader("Authorization") String accessToken, @PathVariable UUID accountNumberID);
    
}
