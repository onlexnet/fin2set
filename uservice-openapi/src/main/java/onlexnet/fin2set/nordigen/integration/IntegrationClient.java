package onlexnet.fin2set.nordigen.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import onlexnet.fin2set.nordigen.generated.Integration;

@FeignClient(value = "Banks", url = "https://ob.nordigen.com/api/v2/institutions")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface IntegrationClient {

        @GetMapping(value = "/?country={country}")
        ResponseEntity<List<Integration>> getListIntegration(
                        @RequestHeader("Authorization") String accessToken,
                        @PathVariable("country") String country);

        @GetMapping(value = "/{BankID}")
        ResponseEntity<Integration> getIntegration(
                        @RequestHeader("Authorization") String accessToken,
                        @PathVariable String BankID);

}
