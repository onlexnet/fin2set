package dj.models.dto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nordigen.PaginatedRequisitionList;

@Service
@AllArgsConstructor
public class PaginatedRequisitionListMapper {

private final RequisitionMapper requisitionMapper;
    
    public PaginatedRequisitionListDTO toDTO(PaginatedRequisitionList paginatedRequisitionList) {
        return new PaginatedRequisitionListDTO()
        .setCount(paginatedRequisitionList.getCount())
        .setNext(paginatedRequisitionList.getNext())
        .setPrevious(paginatedRequisitionList.getPrevious())
        .setResults(requisitionMapper.toDTO(paginatedRequisitionList.getResults()));
    }
}
