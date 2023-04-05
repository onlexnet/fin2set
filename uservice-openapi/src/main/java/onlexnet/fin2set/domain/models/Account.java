package onlexnet.fin2set.domain.models;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;
import onlexnet.fin2set.domain.models.enum_dto.AccountStatusEnumDTO;

@Data
@Accessors(chain = true)
public class Account {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime created;

    @JsonProperty("last_accessed")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime lastAccessed;
    @JsonProperty("iban")
    private String iban;

    @JsonProperty("Bank_id")
    private String BankId;

    @JsonProperty("status")
    private AccountStatusEnumDTO status;

    @JsonProperty("owner_name")
    private String ownerName;

}
