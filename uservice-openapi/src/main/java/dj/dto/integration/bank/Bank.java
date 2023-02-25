package dj.dto.integration.bank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class Bank {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("bic")
    private String bic;

    @JsonProperty("transaction_total_days")
    private String transactionTotalDays;

    @JsonProperty("countries")
    private List<String> countries = new ArrayList<>();

    @JsonProperty("logo")
    private String logo;

}