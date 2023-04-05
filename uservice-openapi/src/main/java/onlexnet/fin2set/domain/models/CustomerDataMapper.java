package onlexnet.fin2set.domain.models;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.api.dto.EndUserAgreementDTO;
import onlexnet.fin2set.domain.models.CustomerData.EndUserAgreementInfo;
import onlexnet.fin2set.nordigen.generated.Requisition;

@Service
public class CustomerDataMapper {

    public CustomerData toDto(Requisition requisition, EndUserAgreementDTO endUserAgreement) {

        EndUserAgreementInfo endUserAgreementInfo = new EndUserAgreementInfo()
                .setCreated(endUserAgreement.getCreated())
                .setMaxHistoricalDays(endUserAgreement.getMaxHistoricalDays())
                .setAccessValidForDays(endUserAgreement.getAccessValidForDays())
                .setAccessScope(endUserAgreement.getAccessScope())
                .setAccepted(endUserAgreement.getAccepted());

        return new CustomerData()
                .setId(requisition.getId())
                .setStatus(requisition.getStatus())
                .setInstitutionId(requisition.getInstitutionId())
                .setAccounts(requisition.getAccounts())
                .setLink(requisition.getLink())
                .setEndUserAgreementInfo(endUserAgreementInfo);
    }

}
