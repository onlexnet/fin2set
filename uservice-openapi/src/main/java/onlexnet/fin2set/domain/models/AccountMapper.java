package onlexnet.fin2set.domain.models;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.domain.models.enum_dto.AccountStatusEnumDTO;
import onlexnet.fin2set.nordigen.generated.Account;

@Service
public class AccountMapper {

    public AccountDTO toDTO(Account account) {
        return new AccountDTO()
        .setId(account.getId())
        .setCreated(account.getCreated())
        .setLastAccessed(account.getLastAccessed())
        .setIban(account.getIban())
        .setInstitutionId(account.getInstitutionId())
        .setStatus(AccountStatusEnumDTO.fromValue(account.getStatus().getValue()))
        .setOwnerName(account.getOwnerName());
    }
    
}
