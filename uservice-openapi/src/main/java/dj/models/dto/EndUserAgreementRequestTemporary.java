package dj.models.dto;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(name = "EndUserAgreement", description = "Represents an end-user agreement.")
public class EndUserAgreementRequestTemporary {

    @JsonProperty("institution_id")
    private String institutionId;

    @JsonProperty("max_historical_days")
    private Integer maxHistoricalDays = 90;

    @JsonProperty("access_valid_for_days")
    private Integer accessValidForDays = 90;

    @JsonProperty("access_scope")
    @Valid
    private List<String> accessScope = null;
}
