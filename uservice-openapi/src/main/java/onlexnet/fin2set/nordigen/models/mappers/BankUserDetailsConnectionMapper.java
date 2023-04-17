package onlexnet.fin2set.nordigen.models.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection.EndUserAgreementInfo;
import onlexnet.fin2set.nordigen.generated.EndUserAgreement;
import onlexnet.fin2set.nordigen.generated.Requisition;

@UtilityClass
public class BankUserDetailsConnectionMapper {

    public static BankUserDetailsConnection fromDTO(Requisition requisition, EndUserAgreement endUserAgreement) {

        EndUserAgreementInfo endUserAgreementInfo = new EndUserAgreementInfo()
                .setCreated(endUserAgreement.getCreated())
                .setMaxHistoricalDays(endUserAgreement.getMaxHistoricalDays())
                .setAccessValidForDays(endUserAgreement.getAccessValidForDays())
                .setAccessScope(endUserAgreement.getAccessScope())
                .setAccepted(endUserAgreement.getAccepted());

        return new BankUserDetailsConnection()
                .setId(requisition.getId())
                .setStatus(requisition.getStatus())
                .setBankId(requisition.getInstitutionId())
                .setAccounts(requisition.getAccounts())
                .setLink(requisition.getLink())
                .setEndUserAgreementInfo(endUserAgreementInfo);
    }

}
