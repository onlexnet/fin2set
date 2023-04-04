package dj.models.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onlexnet.fin2set.nordigen.generated.PaginatedEndUserAgreementList;

@Service
@AllArgsConstructor
public class PaginatedEndUserAgreementsListMapper {

    private final EndUserAgreementMapper endUserAgreementMapper;
    
    public PaginatedEndUserAgreementListDTO toDTO(PaginatedEndUserAgreementList paginatedEndUserAgreementList) {
        return new PaginatedEndUserAgreementListDTO()
        .setCount(paginatedEndUserAgreementList.getCount())
        .setNext(paginatedEndUserAgreementList.getNext())
        .setPrevious(paginatedEndUserAgreementList.getPrevious())
        .setResults(endUserAgreementMapper.toDTO(paginatedEndUserAgreementList.getResults()));
    }

}
