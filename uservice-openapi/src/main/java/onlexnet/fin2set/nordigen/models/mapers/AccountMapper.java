package onlexnet.fin2set.nordigen.models.mapers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.Account;
import onlexnet.fin2set.domain.models.AccountStatusEnum;

@UtilityClass
public class AccountMapper {

    public static Account fromDTO(onlexnet.fin2set.nordigen.generated.Account account) {
        return new Account()  
        .setId(account.getId())
        .setCreated(account.getCreated())
        .setLastAccessed(account.getLastAccessed())
        .setIban(account.getIban())
        .setBankId(account.getInstitutionId())
        .setStatus(AccountStatusEnum.fromValue(account.getStatus().getValue()))
        .setOwnerName(account.getOwnerName());
    }
    
}
