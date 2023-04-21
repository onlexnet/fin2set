package onlexnet.fin2set.domain.models;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BankUserDetailsConnection {

    private UUID id;

    private Object status;

    private String bankId;

    private List<UUID> accounts;

    private URI link;

    private EndUserAgreementInfo endUserAgreementInfo;

    @Data
    @Accessors(chain = true)
    public static class EndUserAgreementInfo {

        private OffsetDateTime created;

        private Integer maxHistoricalDays;

        private Integer accessValidForDays;

        private List<String> accessScope;

        private OffsetDateTime accepted;

    }

}
