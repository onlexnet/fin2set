
package dj.dto.accounts.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetails {

    private String resourceId;

    private String iban;

    private String currency;

    private String ownerName;

    private String name;

    private String product;

    private String cashAccountType;

}
