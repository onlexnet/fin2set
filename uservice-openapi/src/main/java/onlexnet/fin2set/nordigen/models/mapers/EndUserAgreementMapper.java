package onlexnet.fin2set.nordigen.models.mapers;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.EndUserAgreement;

@UtilityClass
public class EndUserAgreementMapper {

    public static EndUserAgreement fromDTO(onlexnet.fin2set.nordigen.generated.EndUserAgreement endUserAgreement) {
        return new EndUserAgreement()
        .setId(endUserAgreement.getId())
        .setCreated(endUserAgreement.getCreated())
        .setBankId(endUserAgreement.getBankId())
        .setMaxHistoricalDays(endUserAgreement.getMaxHistoricalDays())
        .setAccessValidForDays(endUserAgreement.getAccessValidForDays())
        .setAccepted(endUserAgreement.getAccepted())
        .setAccessScope(endUserAgreement.getAccessScope());
    }

    public static List<EndUserAgreement> fromDTO(List<onlexnet.fin2set.nordigen.generated.EndUserAgreement> listEndUserAgreements) {
        var list = new ArrayList<EndUserAgreement>();
        for (onlexnet.fin2set.nordigen.generated.EndUserAgreement endUserAgreement : listEndUserAgreements) {
            list.add(fromDTO(endUserAgreement));
        }
        return list;
    }
    
}
