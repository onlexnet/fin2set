package dj.services.integration.institutions;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import onlexnet.fin2set.nordigen.generated.Integration;

@FeignClient(value = "institutions", url = "https://ob.nordigen.com/api/v2/institutions")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface InstitutionsClient {

        @GetMapping(value = "/?country={country}")
        List<Integration> getListInstitutions(@RequestHeader("Authorization") String accessToken, @PathVariable("country") String country);

        @GetMapping(value = "/{institutionID}")
        Integration getInstitution(@RequestHeader("Authorization") String accessToken, @PathVariable String institutionID);

}
