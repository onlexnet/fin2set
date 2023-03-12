package dj.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dj.services.integration.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.JWTObtainPairRequest;
import nordigen.JWTRefreshRequest;
import nordigen.SpectacularJWTObtain;
import nordigen.SpectacularJWTRefresh;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class IntegrationTokenApi {

    private final TokenService tokenService;

    @PostMapping(value = "/api/v2/token/new/")
    ResponseEntity<SpectacularJWTObtain> createTokens(@RequestBody JWTObtainPairRequest jwtObtainPairRequest) {
        return ResponseEntity.ok(tokenService.getTokens());
    }

    @PostMapping(value = "/api/v2/token/refresh/")
    ResponseEntity<SpectacularJWTRefresh> refreshAccessToken(@RequestBody JWTRefreshRequest jwtRefreshRequest) {
        return ResponseEntity.ok(tokenService.refreshAccessToken());
    }
    
}
