package dj.models.dto;

import org.springframework.stereotype.Service;

import nordigen.SpectacularJWTObtain;

@Service
public class SpectacularJWTObtainMapper {

    public SpectacularJWTObtainDTO toDTO(SpectacularJWTObtain spectacularJWTObtain) {
        return new SpectacularJWTObtainDTO()
        .setAccess(spectacularJWTObtain.getAccess())
        .setAccessExpires(spectacularJWTObtain.getAccessExpires())
        .setRefresh(spectacularJWTObtain.getRefresh())
        .setRefreshExpires(spectacularJWTObtain.getRefreshExpires());
    }
}
