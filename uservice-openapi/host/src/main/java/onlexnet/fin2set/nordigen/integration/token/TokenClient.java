package onlexnet.fin2set.nordigen.integration.token;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.Headers;
import onlexnet.fin2set.nordigen.generated.JWTObtainPairRequest;
import onlexnet.fin2set.nordigen.generated.JWTRefreshRequest;
import onlexnet.fin2set.nordigen.generated.SpectacularJWTObtain;
import onlexnet.fin2set.nordigen.generated.SpectacularJWTRefresh;

@FeignClient(value = "tokens", url = "https://ob.nordigen.com")
@Headers({
        "accept: application/json",
        "Content-Type: application/json" })
public interface TokenClient {

    @PostMapping(value = "/api/v2/token/new/")
    ResponseEntity<SpectacularJWTObtain> createTokens(
            @RequestBody JWTObtainPairRequest jwtObtainPairRequest);

    @PostMapping(value = "/api/v2/token/refresh/")
    ResponseEntity<SpectacularJWTRefresh> refreshAccessToken(
            @RequestBody JWTRefreshRequest jwtRefreshRequest);

}
