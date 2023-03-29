package dj.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpectacularJWTObtainDTO {

    @JsonProperty("access")
    private String access;

    @JsonProperty("access_expires")
    private Integer accessExpires = 86400;

    @JsonProperty("refresh")
    private String refresh;

    @JsonProperty("refresh_expires")
    private Integer refreshExpires = 2592000;

}