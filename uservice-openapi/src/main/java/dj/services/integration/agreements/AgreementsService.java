package dj.services.integration.agreements;

import java.util.Optional;
import java.util.UUID;

import dj.models.dto.EndUserAgreementDTO;
import dj.models.dto.EndUserAgreementRequestTemporary;
import dj.models.dto.PaginatedEndUserAgreementListDTO;

public interface AgreementsService {

    PaginatedEndUserAgreementListDTO getListAllAgreements();


    /**
         * 
         * Used temporary model becouse actually schema nordigen is broken and we are waiting
         * for fix
         */

    EndUserAgreementDTO createAgreement(EndUserAgreementRequestTemporary endUserAgreementRequest);

    /**
     * 
     * @param agreementID
     * @return Some when the result is available
     *         None when result is not found on OpenBank API
     */
    Optional<EndUserAgreementDTO> getAgreement(UUID agreementID);

    void deleteAgreement(UUID agreementID);

}
