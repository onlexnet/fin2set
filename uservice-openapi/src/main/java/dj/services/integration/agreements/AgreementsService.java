package dj.services.integration.agreements;

import nordigen.EndUserAgreement;

public interface AgreementsService {

    EndUserAgreement createAgreement(String institutionId);

    EndUserAgreement getAgreement(String agreementID);
    
}
