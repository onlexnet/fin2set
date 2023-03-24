package dj.services.integration.requistions;

import java.util.Optional;
import java.util.UUID;

import nordigen.PaginatedRequisitionList;
import nordigen.Requisition;
import nordigen.RequisitionRequest;
import nordigen.SpectacularRequisition;

public interface RequisitionsService {

    PaginatedRequisitionList getListAllRequisitions();

    SpectacularRequisition createRequisition(RequisitionRequest RequisitionRequest);

    Optional<Requisition> getRequisition(UUID requisitionsID);

    void deleteRequsition(UUID requisitionsID);

}
