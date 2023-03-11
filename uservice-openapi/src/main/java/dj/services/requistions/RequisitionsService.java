    package dj.services.requistions;

import java.net.URI;

import dj.models.CustomerDataDTO;
import nordigen.PaginatedRequisitionV2List;

public interface RequisitionsService {

    PaginatedRequisitionV2List getListAllRequisitions();

    URI createRequisition(String institutionId);

    CustomerDataDTO getInfoAboutConection(String reference);
}
