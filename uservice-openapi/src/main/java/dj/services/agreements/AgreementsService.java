package dj.services.agreements;

import nordigen.EndUserAgreement;

public interface AgreementsService {

    EndUserAgreement createAgreement(String institutionId);

    EndUserAgreement getAgreement(String agreementID);
    
}
