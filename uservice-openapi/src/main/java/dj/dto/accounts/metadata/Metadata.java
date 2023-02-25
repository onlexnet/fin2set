package dj.dto.accounts.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class Metadata {

    private String id;

    private String created;

    private String lastAccessed;

    private String iban;

    private String institutionId;

    private Status status;

    private String ownerName;

}