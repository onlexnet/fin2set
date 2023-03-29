package dj.models.dto;

import org.springframework.stereotype.Service;

import dj.models.dto.enum_dto.CountryEnumDTO;
import dj.models.dto.enum_dto.Status1c5EnumDTO;
import nordigen.SpectacularRequisition;

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
