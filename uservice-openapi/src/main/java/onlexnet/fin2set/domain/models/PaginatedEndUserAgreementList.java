package onlexnet.fin2set.domain.models;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaginatedEndUserAgreementList {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("next")
    private URI next;

    @JsonProperty("previous")
    private URI previous;

    @JsonProperty("results")
    @Valid
    private List<EndUserAgreement> results;
}
