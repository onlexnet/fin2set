package onlexnet.fin2set.api.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;

@UtilityClass
public class BankUserDetailsConnectionMapper {

  public static onlexnet.fin2set.generated.dto.BankUserDetailsConnection toDTO(BankUserDetailsConnection bankUserDetailsConnection) {
    var bankUserDetailsConnectionDTO = new onlexnet.fin2set.generated.dto.BankUserDetailsConnection();
    bankUserDetailsConnectionDTO.setId(bankUserDetailsConnection.getId());
    bankUserDetailsConnectionDTO.setBankId(bankUserDetailsConnection.getBankId());
    bankUserDetailsConnectionDTO.setAccounts(bankUserDetailsConnection.getAccounts());
    bankUserDetailsConnectionDTO.setStatus(bankUserDetailsConnection.getStatus());
    bankUserDetailsConnectionDTO.setLink(bankUserDetailsConnection.getLink());
    bankUserDetailsConnectionDTO.setEndUserAgreementInfo(bankUserDetailsConnection.getAccounts());
  }
  
}
