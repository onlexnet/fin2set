package dj.services.token;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import nordigen.JWTObtainPairRequest;
import nordigen.JWTRefreshRequest;
import nordigen.SpectacularJWTObtain;
import nordigen.SpectacularJWTRefresh;

@FeignClient(value = "tokens", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json"})
public interface TokenClient {

    @PostMapping(value = "/api/v2/token/new/")
    SpectacularJWTObtain createTokens(@RequestBody JWTObtainPairRequest jwtObtainPairRequest);

    @PostMapping(value = "/api/v2/token/refresh/")
    SpectacularJWTRefresh refreshAccessToken(@RequestBody JWTRefreshRequest jwtRefreshRequest);
    
}
