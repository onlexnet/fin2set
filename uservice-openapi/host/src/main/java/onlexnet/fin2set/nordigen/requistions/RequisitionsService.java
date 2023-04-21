package onlexnet.fin2set.nordigen.requistions;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import lombok.Value;
import onlexnet.fin2set.domain.models.PaginatedRequisitionList;
import onlexnet.fin2set.domain.models.Requisition;
import onlexnet.fin2set.domain.models.SpectacularRequisition;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;

public interface RequisitionsService {

    PaginatedRequisitionList getListAllRequisitions();

    SpectacularRequisition createRequisition(RequisitionRequest requisitionRequest);

    Optional<Requisition> getRequisition(UUID requisitionsID);

    void deleteRequsition(UUID requisitionsID);

    CreateRequisitionResult createRequisition(URI webhookAddress, String bankID, String myReference, UUID agreementId);

    @Value(staticConstructor = "of")
    class CreateRequisitionResult {
        UUID id;
        URI continuationLink;
    }

}
