package dj.models.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dj.models.dto.enum_dto.CountryEnumDTO;
import dj.models.dto.enum_dto.Status1c5EnumDTO;
import nordigen.Requisition;

@Service
public class RequisitionMapper {

    public RequisitionDTO toDTO(Requisition requisition) {
        return new RequisitionDTO()
        .setId(requisition.getId())
        .setCreated(requisition.getCreated())
        .setRedirect(requisition.getRedirect())
        .setStatus(Status1c5EnumDTO.fromValue(requisition.getStatus().getValue()))
        .setInstitutionId(requisition.getInstitutionId())
        .setAgreement(requisition.getAgreement())
        .setReference(requisition.getReference())
        .setAccounts(requisition.getAccounts())
        .setUserLanguage(CountryEnumDTO.fromValue(requisition.getUserLanguage()))
        .setLink(requisition.getLink())
        .setSsn(requisition.getSsn())
        .setAccountSelection(requisition.getAccountSelection())
        .setRedirectImmediate(requisition.getRedirectImmediate());
    }

    public List<RequisitionDTO> toDTO(List<Requisition> listRequisitions) {
        var list = new ArrayList<RequisitionDTO>();
        for (Requisition requisition : listRequisitions) {
            list.add(toDTO(requisition));
        }
        return list;
    }
    
}
