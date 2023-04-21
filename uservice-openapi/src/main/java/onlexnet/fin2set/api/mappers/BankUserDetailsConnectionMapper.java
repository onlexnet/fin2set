package onlexnet.fin2set.api.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection.EndUserAgreementInfo;

@UtilityClass
public class BankUserDetailsConnectionMapper {

  public static onlexnet.fin2set.generated.dto.BankUserDetailsConnection toDTO(BankUserDetailsConnection bankUserDetailsConnection) {
    var bankUserDetailsConnectionDTO = new onlexnet.fin2set.generated.dto.BankUserDetailsConnection();
    bankUserDetailsConnectionDTO.setId(bankUserDetailsConnection.getId());
    bankUserDetailsConnectionDTO.setBankId(bankUserDetailsConnection.getBankId());
    bankUserDetailsConnectionDTO.setAccounts(bankUserDetailsConnection.getAccounts());
    bankUserDetailsConnectionDTO.setStatus(bankUserDetailsConnection.getStatus());
    bankUserDetailsConnectionDTO.setLink(bankUserDetailsConnection.getLink());
    bankUserDetailsConnectionDTO.setEndUserAgreementInfo(toDTO(bankUserDetailsConnection.getEndUserAgreementInfo()));
    return bankUserDetailsConnectionDTO;
  }

  private onlexnet.fin2set.generated.dto.EndUserAgreementInfo toDTO(EndUserAgreementInfo endUserAgreementInfo) {
    var endUserAgreementInfoDTO = new onlexnet.fin2set.generated.dto.EndUserAgreementInfo();
    endUserAgreementInfoDTO.setAccepted(endUserAgreementInfo.getAccepted());
    endUserAgreementInfoDTO.setAccessScope(endUserAgreementInfo.getAccessScope());
    endUserAgreementInfoDTO.setAccessValidForDays(endUserAgreementInfo.getAccessValidForDays());
    endUserAgreementInfoDTO.setCreated(endUserAgreementInfo.getCreated());
    endUserAgreementInfoDTO.setMaxHistoricalDays(endUserAgreementInfo.getMaxHistoricalDays());
    return endUserAgreementInfoDTO;
  }
  
}
