package dj.services.integration.agreements;

import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;
import nordigen.PaginatedEndUserAgreementList;

public interface AgreementsService {

    PaginatedEndUserAgreementList getListAllAgreements();

    EndUserAgreement createAgreement(EndUserAgreementRequest endUserAgreementRequest);

    EndUserAgreement getAgreement(String agreementID);

    void deleteAgreement(String agreementID);
    
}
