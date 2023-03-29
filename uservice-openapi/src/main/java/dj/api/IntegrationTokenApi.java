package dj.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dj.models.dto.SpectacularJWTObtainDTO;
import dj.models.dto.SpectacularJWTRefreshDTO;
import dj.services.integration.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.JWTObtainPairRequest;
import nordigen.JWTRefreshRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class IntegrationTokenApi {

    private final TokenService tokenService;

    @PostMapping(value = "/api/v2/token/new/")
    ResponseEntity<SpectacularJWTObtainDTO> createTokens(@RequestBody JWTObtainPairRequest jwtObtainPairRequest) {
        return ResponseEntity.ok(tokenService.getTokens());
    }

    @PostMapping(value = "/api/v2/token/refresh/")
    ResponseEntity<SpectacularJWTRefreshDTO> refreshAccessToken(@RequestBody JWTRefreshRequest jwtRefreshRequest) {
        return ResponseEntity.ok(tokenService.refreshAccessToken());
    }
    
}
