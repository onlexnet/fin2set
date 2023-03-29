package dj.models.dto;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaginatedEndUserAgreementListDTO {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("next")
    private URI next = null;

    @JsonProperty("previous")
    private URI previous = null;

    @JsonProperty("results")
    @Valid
    private List<EndUserAgreementDTO> results = null;
}
