package onlexnet.fin2set.domain.models;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PaginatedRequisitionList {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("next")
    private URI next;

    @JsonProperty("previous")
    private URI previous;

    @JsonProperty("results")
    private List<Requisition> results;
}
