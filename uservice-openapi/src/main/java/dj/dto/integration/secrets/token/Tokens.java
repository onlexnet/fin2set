package dj.dto.integration.secrets.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class Tokens {

    @JsonProperty("access")
    private String access;

    @JsonProperty("access_expires")
    private Integer accessExpires;

    @JsonProperty("refresh")
    private String refresh;

    @JsonProperty("refresh_expires")
    private Integer refreshExpires;

}
