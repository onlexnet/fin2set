package dj.models.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import dj.models.dto.enum_dto.AccountStatusEnumDTO;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime created;

    @JsonProperty("last_accessed")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime lastAccessed = null;

    @JsonProperty("iban")
    private String iban;

    @JsonProperty("institution_id")
    private String institutionId;

    @JsonProperty("status")
    private AccountStatusEnumDTO status;

    @JsonProperty("owner_name")
    private String ownerName;

}
