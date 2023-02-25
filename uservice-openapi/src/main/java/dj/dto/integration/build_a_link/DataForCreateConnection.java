package dj.dto.integration.build_a_link;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class DataForCreateConnection {

    @JsonProperty("redirect")
    public String redirect;

    @JsonProperty("institution_id")
    public String institutionId;

    @JsonProperty("reference")
    public String reference;

    @JsonProperty("agreement")
    public String agreement;

    @JsonProperty("user_language")
    public String userLanguage;

}
