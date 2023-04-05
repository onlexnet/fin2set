package onlexnet.fin2set.nordigen.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.SpectacularRequisition;
import onlexnet.fin2set.domain.models.enum_dto.CountryEnumDTO;
import onlexnet.fin2set.domain.models.enum_dto.Status1c5EnumDTO;

@UtilityClass
public class SpectacularRequisitionMapper {

    public static SpectacularRequisition fromDTO(onlexnet.fin2set.nordigen.generated.SpectacularRequisition spectacularRequisition) {
        return new SpectacularRequisition()
        .setId(spectacularRequisition.getId())
        .setCreated(spectacularRequisition.getCreated())
        .setRedirect(spectacularRequisition.getRedirect())
        .setStatus(Status1c5EnumDTO.fromValue(spectacularRequisition.getStatus().getValue()))
        .setBankId(spectacularRequisition.getInstitutionId())
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
