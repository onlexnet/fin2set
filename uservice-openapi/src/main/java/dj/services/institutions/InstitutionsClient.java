package dj.services.institutions;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import nordigen.Integration;

@FeignClient(value = "institutions", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface InstitutionsClient {

    @GetMapping(value = "/api/v2/institutions/?country={country}")
    List<Integration> getBankList(@RequestHeader("Authorization") String accessToken,
                           @PathVariable("country") String country);
    
}
