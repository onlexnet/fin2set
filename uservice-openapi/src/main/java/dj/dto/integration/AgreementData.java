package dj.dto.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class AgreementData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("created")
    private String created;

    @JsonProperty("max_historical_days")
    private int maxHistoricalDays;

    @JsonProperty("access_valid_for_days")
    private int accessValidForDays;

    @JsonProperty("access_scope")
    private List<String> accessScope = new ArrayList<>();

    @JsonProperty("accepted")
    private String accepted;

    @JsonProperty("institution_id")
    private String institutionId;

}