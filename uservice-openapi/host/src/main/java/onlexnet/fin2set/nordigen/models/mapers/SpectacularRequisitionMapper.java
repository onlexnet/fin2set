package onlexnet.fin2set.nordigen.models.mapers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.SpectacularRequisition;
import onlexnet.fin2set.domain.models.CountryEnum;
import onlexnet.fin2set.domain.models.Status1c5Enum;

@UtilityClass
public class SpectacularRequisitionMapper {

    public static SpectacularRequisition fromDTO(onlexnet.fin2set.nordigen.generated.SpectacularRequisition spectacularRequisition) {
        return new SpectacularRequisition()
        .setId(spectacularRequisition.getId())
        .setCreated(spectacularRequisition.getCreated())
        .setRedirect(spectacularRequisition.getRedirect())
        .setStatus(Status1c5Enum.fromValue(spectacularRequisition.getStatus().getValue()))
        .setBankId(spectacularRequisition.getInstitutionId())
        .setAgreement(spectacularRequisition.getAgreement())
        .setReference(spectacularRequisition.getReference())
        .setAccounts(spectacularRequisition.getAccounts())
        .setUserLanguage(CountryEnum.fromValue(spectacularRequisition.getUserLanguage()))
        .setLink(spectacularRequisition.getLink())
        .setSsn(spectacularRequisition.getSsn())
        .setAccountSelection(spectacularRequisition.getAccountSelection())
        .setRedirectImmediate(spectacularRequisition.getRedirectImmediate());
    }
    
    
}
