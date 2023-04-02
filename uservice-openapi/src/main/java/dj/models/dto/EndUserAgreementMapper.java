package dj.models.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import nordigen.EndUserAgreement;

@Service
public class EndUserAgreementMapper {

    public EndUserAgreementDTO toDTO(EndUserAgreement endUserAgreement) {
        return new EndUserAgreementDTO()
        .setId(endUserAgreement.getId())
        .setCreated(endUserAgreement.getCreated())
        .setInstitutionId(endUserAgreement.getInstitutionId())
        .setMaxHistoricalDays(endUserAgreement.getMaxHistoricalDays())
        .setAccessValidForDays(endUserAgreement.getAccessValidForDays())
        .setAccepted(endUserAgreement.getAccepted())
        .setAccessScope(endUserAgreement.getAccessScope());
    }

    public List<EndUserAgreementDTO> toDTO(List<EndUserAgreement> listEndUserAgreements) {
        var list = new ArrayList<EndUserAgreementDTO>();
        for (EndUserAgreement endUserAgreement : listEndUserAgreements) {
            list.add(toDTO(endUserAgreement));
        }
        return list;
    }
    
}
