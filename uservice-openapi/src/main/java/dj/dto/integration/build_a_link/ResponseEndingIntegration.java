
package dj.dto.integration.build_a_link;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class ResponseEndingIntegration {

    @JsonProperty("id")
    private String id;

    @JsonProperty("created")
    private String created;

    @JsonProperty("redirect")
    private String redirect;

    @JsonProperty("status")
    private String status;

    @JsonProperty("institution_id")
    private String institutionId;

    @JsonProperty("agreement")
    private String agreement;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("accounts")
    private List<Object> accounts = new ArrayList<>();

    @JsonProperty("user_language")
    private String userLanguage;

    @JsonProperty("link")
    private String link;

    @JsonProperty("ssn")
    private String ssn;

    @JsonProperty("account_selection")
    private boolean accountSelection;

    @JsonProperty("redirect_immediate")
    private boolean redirectImmediate;

}
