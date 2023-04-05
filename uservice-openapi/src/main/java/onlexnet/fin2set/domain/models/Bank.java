package onlexnet.fin2set.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

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
    @Valid
    private List<CountryEnum> countries = new ArrayList<>();

    @JsonProperty("logo")
    private String logo;
}