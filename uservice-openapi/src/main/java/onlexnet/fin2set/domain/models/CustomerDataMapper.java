package onlexnet.fin2set.domain.models;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.CustomerData.EndUserAgreementInfo;

@UtilityClass
public class CustomerDataMapper {

    public static CustomerData fromDTO(Requisition requisition, EndUserAgreement endUserAgreement) {

        EndUserAgreementInfo endUserAgreementInfo = new EndUserAgreementInfo()
                .setCreated(endUserAgreement.getCreated())
                .setMaxHistoricalDays(endUserAgreement.getMaxHistoricalDays())
                .setAccessValidForDays(endUserAgreement.getAccessValidForDays())
                .setAccessScope(endUserAgreement.getAccessScope())
                .setAccepted(endUserAgreement.getAccepted());

        return new CustomerData()
                .setId(requisition.getId())
                .setStatus(requisition.getStatus())
                .setBankId(requisition.getBankId())
                .setAccounts(requisition.getAccounts())
                .setLink(requisition.getLink())
                .setEndUserAgreementInfo(endUserAgreementInfo);
    }

}
