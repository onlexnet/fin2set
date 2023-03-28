package dj.models.dto;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaginatedRequisitionListDTO {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("next")
    private URI next = null;

    @JsonProperty("previous")
    private URI previous = null;

    @JsonProperty("results")
    @Valid
    private List<RequisitionDTO> results = null;
}
