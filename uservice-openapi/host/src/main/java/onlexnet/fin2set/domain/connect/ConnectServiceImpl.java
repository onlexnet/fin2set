package onlexnet.fin2set.domain.connect;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onlexnet.fin2set.domain.models.CustomerData;
import onlexnet.fin2set.domain.models.CustomerDataMapper;
import onlexnet.fin2set.nordigen.agreements.AgreementsService;
import onlexnet.fin2set.nordigen.requistions.RequisitionsService;

@Service
@AllArgsConstructor
public class ConnectServiceImpl implements ConnectService {

    private final RequisitionsService requisitionsService;
    private final AgreementsService agreementsService;

    // TODO 1 - create concurrent map
    // TODO 2 - move to distributed cache
    // TODO 3 - support configurable timeout
    private Map<String, UUID> mapReferenceRequisitionsID = new HashMap<>();

    @Override
    public URI createLinkToConnect(String bankID) {

        final var maxHistoricalDays = 90;
        final var accessValidForDays = 30;

        var agreementId = agreementsService.createAgreement(bankID, maxHistoricalDays, accessValidForDays);

        var myReference = UUID.randomUUID().toString();

        // TODO - address should be configured as hosted protocol, address and port will be different per environment
        var webhookAddress = URI.create("http://192.168.0.100:8080/api/integration/info");

        var requisitionsResult = requisitionsService.createRequisition(webhookAddress, bankID, myReference, agreementId);
        mapReferenceRequisitionsID.put(myReference, requisitionsResult.getId());

        return requisitionsResult.getContinuationLink();
    }

    @Override
    public CustomerData getInfoAboutConection(String reference) {

        var requisitionsID = mapReferenceRequisitionsID.get(reference);
        var requisition = requisitionsService.getRequisition(requisitionsID).orElseThrow();
        var endUserAgreement = agreementsService.getAgreement(requisition.getAgreement()).orElseThrow();

        return CustomerDataMapper.fromDTO(requisition, endUserAgreement);    
    }
    
}
