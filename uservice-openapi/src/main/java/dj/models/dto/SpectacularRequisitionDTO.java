package dj.models.dto;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import dj.models.dto.enum_dto.CountryEnumDTO;
import dj.models.dto.enum_dto.Status1c5EnumDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SpectacularRequisitionDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime created;

    @JsonProperty("redirect")
    private URI redirect;

    @JsonProperty("status")
    private Status1c5EnumDTO status;

    @JsonProperty("institution_id")
    private String institutionId;

    @JsonProperty("agreement")
    private UUID agreement;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("accounts")
    @Valid
    private List<Object> accounts;

    @JsonProperty("user_language")
    private CountryEnumDTO userLanguage;

    @JsonProperty("link")
    private URI link;

    @JsonProperty("ssn")
    private String ssn;

    @JsonProperty("account_selection")
    private Boolean accountSelection;

    @JsonProperty("redirect_immediate")
    private Boolean redirectImmediate;
}
