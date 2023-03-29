package dj.models.dto;

import org.springframework.stereotype.Service;

import nordigen.SpectacularJWTRefresh;

@Service
public class SpectacularJWTRefreshMapper {
    
    public SpectacularJWTRefreshDTO toDTO(SpectacularJWTRefresh spectacularJWTRefresh) {
        return new SpectacularJWTRefreshDTO()
        .setAccess(spectacularJWTRefresh.getAccess())
        .setAccessExpires(spectacularJWTRefresh.getAccessExpires());
    }
}
