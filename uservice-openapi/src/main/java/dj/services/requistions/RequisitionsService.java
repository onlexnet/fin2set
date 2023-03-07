    package dj.services.requistions;

import java.net.URI;

import nordigen.RequisitionV2;

public interface RequisitionsService {

    URI createRequisition(String institutionId);

    RequisitionV2 getListAccounts(String reference);
}
