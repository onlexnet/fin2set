package onlexnet.fin2set.domain.models;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.domain.models.enum_dto.CountryEnumDTO;
import onlexnet.fin2set.domain.models.enum_dto.Status1c5EnumDTO;
import onlexnet.fin2set.nordigen.generated.SpectacularRequisition;

@Service
public class SpectacularRequisitionMapper {

    public SpectacularRequisitionDTO toDTO(SpectacularRequisition spectacularRequisition) {
        return new SpectacularRequisitionDTO()
        .setId(spectacularRequisition.getId())
        .setCreated(spectacularRequisition.getCreated())
        .setRedirect(spectacularRequisition.getRedirect())
        .setStatus(Status1c5EnumDTO.fromValue(spectacularRequisition.getStatus().getValue()))
        .setInstitutionId(spectacularRequisition.getInstitutionId())
        .setAgreement(spectacularRequisition.getAgreement())
        .setReference(spectacularRequisition.getReference())
        .setAccounts(spectacularRequisition.getAccounts())
        .setUserLanguage(CountryEnumDTO.fromValue(spectacularRequisition.getUserLanguage()))
        .setLink(spectacularRequisition.getLink())
        .setSsn(spectacularRequisition.getSsn())
        .setAccountSelection(spectacularRequisition.getAccountSelection())
        .setRedirectImmediate(spectacularRequisition.getRedirectImmediate());
    }
    
    
}
