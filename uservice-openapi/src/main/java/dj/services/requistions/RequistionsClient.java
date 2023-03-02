package dj.services.requistions;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dj.dto.integration.build_a_link.DataForCreateConnection;
import dj.dto.integration.build_a_link.ResponseEndingIntegration;
import feign.Headers;

@FeignClient(value = "requistions", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface RequistionsClient {

    @PostMapping(value = "/api/v2/requisitions/")
    ResponseEndingIntegration createConnection(@RequestHeader("Authorization") String accessToken,
                                               @RequestBody DataForCreateConnection dataForCreateConnection);
    
}
