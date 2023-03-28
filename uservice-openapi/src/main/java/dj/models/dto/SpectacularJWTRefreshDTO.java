package dj.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpectacularJWTRefreshDTO {

    @JsonProperty("access")
    private String access;

    @JsonProperty("access_expires")
    private Integer accessExpires = 86400;

}
