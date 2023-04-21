package onlexnet.fin2set.nordigen.mappers;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.PaginatedRequisitionList;

@UtilityClass
public class PaginatedRequisitionListMapper {
    
    public static PaginatedRequisitionList fromDTO(onlexnet.fin2set.nordigen.generated.PaginatedRequisitionList paginatedRequisitionList) {
        return new PaginatedRequisitionList()
        .setCount(paginatedRequisitionList.getCount())
        .setNext(paginatedRequisitionList.getNext())
        .setPrevious(paginatedRequisitionList.getPrevious())
        .setResults(RequisitionMapper.fromDTO(paginatedRequisitionList.getResults()));
    }
}
