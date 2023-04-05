package onlexnet.fin2set.domain.models;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EndUserAgreement {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime created;

    @JsonProperty("Bank_id")
    private String bankId;

    @JsonProperty("max_historical_days")
    private Integer maxHistoricalDays;

    @JsonProperty("access_valid_for_days")
    private Integer accessValidForDays;

    @JsonProperty("access_scope")
    @Valid
    private List<String> accessScope;

    @JsonProperty("accepted")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime accepted;
}
