package onlexnet.fin2set.api.dto;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.nordigen.generated.SpectacularJWTRefresh;

@Service
public class SpectacularJWTRefreshMapper {
    
    public SpectacularJWTRefreshDTO toDTO(SpectacularJWTRefresh spectacularJWTRefresh) {
        return new SpectacularJWTRefreshDTO()
        .setAccess(spectacularJWTRefresh.getAccess())
        .setAccessExpires(spectacularJWTRefresh.getAccessExpires());
    }
}
