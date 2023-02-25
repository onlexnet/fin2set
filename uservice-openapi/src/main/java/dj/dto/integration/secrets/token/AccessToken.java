package dj.dto.integration.secrets.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class AccessToken {

    private String access;

    private Integer accessExpires;
}
