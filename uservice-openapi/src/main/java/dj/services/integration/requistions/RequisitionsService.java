package dj.services.integration.requistions;

import java.util.Optional;
import java.util.UUID;

import dj.models.dto.PaginatedRequisitionListDTO;
import dj.models.dto.RequisitionDTO;
import dj.models.dto.SpectacularRequisitionDTO;
import nordigen.RequisitionRequest;

public interface RequisitionsService {

    PaginatedRequisitionListDTO getListAllRequisitions();

    SpectacularRequisitionDTO createRequisition(RequisitionRequest RequisitionRequest);

    Optional<RequisitionDTO> getRequisition(UUID requisitionsID);

    void deleteRequsition(UUID requisitionsID);

}
