package dj.services.integration.agreements;

import java.util.Optional;
import java.util.UUID;

import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;
import nordigen.PaginatedEndUserAgreementList;

public interface AgreementsService {

    PaginatedEndUserAgreementList getListAllAgreements();

    EndUserAgreement createAgreement(EndUserAgreementRequest endUserAgreementRequest);

    /**
     * 
     * @param agreementID
     * @return Some when the result is available
     *         None when result is not found on OpenBank API 
     */
    Optional<EndUserAgreement> getAgreement(UUID agreementID);

    void deleteAgreement(UUID agreementID);
    

}
