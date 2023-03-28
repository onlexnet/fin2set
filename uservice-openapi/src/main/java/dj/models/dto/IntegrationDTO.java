package dj.models.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import dj.models.dto.enum_dto.CountryEnumDTO;

public class IntegrationDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("bic")
    private String bic;

    @JsonProperty("transaction_total_days")
    private String transactionTotalDays = "90";

    @JsonProperty("countries")
    @Valid
    private List<CountryEnumDTO> countries = new ArrayList<>();

    @JsonProperty("logo")
    private String logo;
}