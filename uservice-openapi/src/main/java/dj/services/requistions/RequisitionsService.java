    package dj.services.requistions;

import java.net.URI;

import dj.models.CustomerDataDTO;

public interface RequisitionsService {

    URI createRequisition(String institutionId);

    CustomerDataDTO getInfoAboutConection(String reference);
}
