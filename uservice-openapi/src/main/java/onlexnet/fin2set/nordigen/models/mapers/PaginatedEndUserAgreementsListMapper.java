package onlexnet.fin2set.nordigen.models.mapers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.PaginatedEndUserAgreementList;

@UtilityClass
public class PaginatedEndUserAgreementsListMapper {
    
    public static PaginatedEndUserAgreementList fromDTO(onlexnet.fin2set.nordigen.generated.PaginatedEndUserAgreementList paginatedEndUserAgreementList) {
        return new PaginatedEndUserAgreementList()
        .setCount(paginatedEndUserAgreementList.getCount())
        .setNext(paginatedEndUserAgreementList.getNext())
        .setPrevious(paginatedEndUserAgreementList.getPrevious())
        .setResults(EndUserAgreementMapper.fromDTO(paginatedEndUserAgreementList.getResults()));
    }

}
