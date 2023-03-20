package dj.services.integration.requistions;

import java.util.Optional;
import java.util.UUID;

import nordigen.PaginatedRequisitionV2List;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularRequisitionV2;

public interface RequisitionsService {

    PaginatedRequisitionV2List getListAllRequisitions();

    SpectacularRequisitionV2 createRequisition(RequisitionV2Request requisitionV2Request);

    Optional<RequisitionV2> getRequisition(UUID requisitionsID);

    void deleteRequsition(UUID requisitionsID);

}
