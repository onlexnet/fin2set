package dj.services.integration.agreements;

import java.util.Optional;

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
     *         None whe nresult is not found on OpenBank API 
     */
    Optional<EndUserAgreement> getAgreement(String agreementID);

    void deleteAgreement(String agreementID);
    

}
