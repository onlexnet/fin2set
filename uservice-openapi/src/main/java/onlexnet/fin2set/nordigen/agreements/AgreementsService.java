package onlexnet.fin2set.nordigen.agreements;

import java.util.Optional;
import java.util.UUID;

import onlexnet.fin2set.api.dto.EndUserAgreementDTO;
import onlexnet.fin2set.api.dto.PaginatedEndUserAgreementListDTO;
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
