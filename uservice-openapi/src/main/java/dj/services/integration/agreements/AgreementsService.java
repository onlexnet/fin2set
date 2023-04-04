package dj.services.integration.agreements;

import java.util.Optional;
import java.util.UUID;

import dj.models.dto.EndUserAgreementDTO;
import dj.models.dto.PaginatedEndUserAgreementListDTO;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;

public interface AgreementsService {

    PaginatedEndUserAgreementListDTO getListAllAgreements();

    EndUserAgreementDTO createAgreement(EndUserAgreementRequest endUserAgreementRequest);

    /**
     * 
     * @param agreementID
     * @return Some when the result is available
     *         None when result is not found on OpenBank API
     */
    Optional<EndUserAgreementDTO> getAgreement(UUID agreementID);

    void deleteAgreement(UUID agreementID);

}
