package dj.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpectacularJWTRefreshDTO {

    @JsonProperty("access")
    private String access;

    @JsonProperty("access_expires")
    private Integer accessExpires = 86400;

}
