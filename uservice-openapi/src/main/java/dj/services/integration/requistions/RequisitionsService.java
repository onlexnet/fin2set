package dj.services.integration.requistions;

import nordigen.PaginatedRequisitionV2List;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularRequisitionV2;

public interface RequisitionsService {

    PaginatedRequisitionV2List getListAllRequisitions();

    SpectacularRequisitionV2 createRequisition(RequisitionV2Request requisitionV2Request);

    RequisitionV2 getRequisition(String requisitionsID);

    void deleteRequsition();

}
