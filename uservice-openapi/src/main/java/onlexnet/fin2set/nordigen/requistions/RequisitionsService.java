package onlexnet.fin2set.nordigen.requistions;

import java.util.Optional;
import java.util.UUID;

import onlexnet.fin2set.domain.models.PaginatedRequisitionList;
import onlexnet.fin2set.domain.models.Requisition;
import onlexnet.fin2set.domain.models.SpectacularRequisition;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;

public interface RequisitionsService {

    PaginatedRequisitionList getListAllRequisitions();

    SpectacularRequisition createRequisition(RequisitionRequest requisitionRequest);

    Optional<Requisition> getRequisition(UUID requisitionsID);

    void deleteRequsition(UUID requisitionsID);

}
