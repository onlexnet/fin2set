package dj.services.token;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import nordigen.JWTObtainPairRequest;
import nordigen.SpectacularJWTObtain;

@FeignClient(value = "integration", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface TokenClient {

    @PostMapping(value = "/api/v2/token/new/")
    SpectacularJWTObtain createTokens(@RequestBody JWTObtainPairRequest jwtObtainPairRequest);
    
}
