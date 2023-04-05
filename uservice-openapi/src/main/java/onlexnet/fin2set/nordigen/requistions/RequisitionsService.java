package onlexnet.fin2set.nordigen.requistions;

import java.util.Optional;
import java.util.UUID;

import onlexnet.fin2set.domain.models.PaginatedRequisitionListDTO;
import onlexnet.fin2set.domain.models.RequisitionDTO;
import onlexnet.fin2set.domain.models.SpectacularRequisitionDTO;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;

public interface RequisitionsService {

    PaginatedRequisitionListDTO getListAllRequisitions();

    SpectacularRequisitionDTO createRequisition(RequisitionRequest requisitionRequest);

    Optional<RequisitionDTO> getRequisition(UUID requisitionsID);

    void deleteRequsition(UUID requisitionsID);

}
