package onlexnet.fin2set.nordigen.requistions;

import java.util.Optional;
import java.util.UUID;

import onlexnet.fin2set.api.dto.PaginatedRequisitionListDTO;
import onlexnet.fin2set.api.dto.RequisitionDTO;
import onlexnet.fin2set.api.dto.SpectacularRequisitionDTO;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;

public interface RequisitionsService {

    PaginatedRequisitionListDTO getListAllRequisitions();

    SpectacularRequisitionDTO createRequisition(RequisitionRequest requisitionRequest);

    Optional<RequisitionDTO> getRequisition(UUID requisitionsID);

    void deleteRequsition(UUID requisitionsID);

}
