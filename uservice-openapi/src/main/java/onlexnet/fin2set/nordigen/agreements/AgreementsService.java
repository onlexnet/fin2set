package onlexnet.fin2set.nordigen.agreements;

import java.util.Optional;
import java.util.UUID;

import onlexnet.fin2set.domain.models.EndUserAgreement;
import onlexnet.fin2set.domain.models.PaginatedEndUserAgreementList;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;

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
