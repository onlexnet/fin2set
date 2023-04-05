package onlexnet.fin2set.nordigen.models.mapers;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.Requisition;
import onlexnet.fin2set.domain.models.enum_dto.CountryEnumDTO;
import onlexnet.fin2set.domain.models.enum_dto.Status1c5EnumDTO;

@UtilityClass
public class RequisitionMapper {

    public static Requisition fromDTO(onlexnet.fin2set.nordigen.generated.Requisition requisition) {
        return new Requisition()
        .setId(requisition.getId())
        .setCreated(requisition.getCreated())
        .setRedirect(requisition.getRedirect())
        .setStatus(Status1c5EnumDTO.fromValue(requisition.getStatus().getValue()))
        .setBankId(requisition.getBankId())
        .setAgreement(requisition.getAgreement())
        .setReference(requisition.getReference())
        .setAccounts(requisition.getAccounts())
        .setUserLanguage(CountryEnumDTO.fromValue(requisition.getUserLanguage()))
        .setLink(requisition.getLink())
        .setSsn(requisition.getSsn())
        .setAccountSelection(requisition.getAccountSelection())
        .setRedirectImmediate(requisition.getRedirectImmediate());
    }

    public static List<Requisition> fromDTO(List<onlexnet.fin2set.nordigen.generated.Requisition> listRequisitions) {
        var list = new ArrayList<Requisition>();
        for (onlexnet.fin2set.nordigen.generated.Requisition requisition : listRequisitions) {
            list.add(fromDTO(requisition));
        }
        return list;
    }
    
}
