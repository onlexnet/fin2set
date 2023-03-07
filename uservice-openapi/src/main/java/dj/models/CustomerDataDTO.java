package dj.models;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerDataDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("status")
    private Object status;

    @JsonProperty("institution_id")
    private String institutionId;

    @JsonProperty("accounts")
    private List<UUID> accounts;

    @JsonProperty("link")
    private String link = "https://ob.nordigen.com/psd2/start/3fa85f64-5717-4562-b3fc-2c963f66afa6/{$INSTITUTION_ID}";

    @JsonProperty("end_user_agreement_info")
    private EndUserAgreementInfo endUserAgreementInfo;


    @Data
    @Accessors(chain = true)
    public static class EndUserAgreementInfo {

        @JsonProperty("created")
        private OffsetDateTime created;

        @JsonProperty("max_historical_days")
        private Integer maxHistoricalDays;

        @JsonProperty("access_valid_for_days")
        private Integer accessValidForDays;

        @JsonProperty("access_scope")
        private List<String> accessScope;

        @JsonProperty("accepted")
        private OffsetDateTime accepted;

    }

}
