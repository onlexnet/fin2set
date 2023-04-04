package dj.models;

import org.springframework.stereotype.Service;

import dj.models.CustomerDataDTO.EndUserAgreementInfo;
import dj.models.dto.EndUserAgreementDTO;
import onlexnet.fin2set.nordigen.generated.Requisition;

@Service
public class CustomerDataMapper {

    public CustomerDataDTO toDto(Requisition requisition, EndUserAgreementDTO endUserAgreement) {

        EndUserAgreementInfo endUserAgreementInfo = new EndUserAgreementInfo()
                .setCreated(endUserAgreement.getCreated())
                .setMaxHistoricalDays(endUserAgreement.getMaxHistoricalDays())
                .setAccessValidForDays(endUserAgreement.getAccessValidForDays())
                .setAccessScope(endUserAgreement.getAccessScope())
                .setAccepted(endUserAgreement.getAccepted());

        return new CustomerDataDTO()
                .setId(requisition.getId())
                .setStatus(requisition.getStatus())
                .setInstitutionId(requisition.getInstitutionId())
                .setAccounts(requisition.getAccounts())
                .setLink(requisition.getLink())
                .setEndUserAgreementInfo(endUserAgreementInfo);
    }

}
