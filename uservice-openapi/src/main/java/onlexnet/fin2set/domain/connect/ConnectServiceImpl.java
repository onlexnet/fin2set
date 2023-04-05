package onlexnet.fin2set.domain.connect;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import onlexnet.fin2set.domain.models.CustomerData;
import onlexnet.fin2set.domain.models.CustomerDataMapper;
import onlexnet.fin2set.nordigen.agreements.AgreementsService;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;
import onlexnet.fin2set.nordigen.requistions.RequisitionsService;

@Service
@AllArgsConstructor
public class ConnectServiceImpl implements ConnectService {

    private final RequisitionsService requisitionsService;
    private final AgreementsService agreementsService;

    private Map<String, UUID> mapReferenceRequisitionsID = new HashMap<>();

    @Override
    public URI createLinkToConnect(String bankID) {

        var endUserAgreementRequest = new EndUserAgreementRequest()
                .institutionId(bankID)
                .maxHistoricalDays(90)
                .accessValidForDays(30)
                .accessScope(List.of("balances", "details", "transactions"));  

        var endUserAgreement = agreementsService.createAgreement(endUserAgreementRequest);

        var reference = UUID.randomUUID().toString();

        var requisitionRequest = new RequisitionRequest()
                .redirect(URI.create("http://localhost:8080/api/integration/info"))
                .institutionId(bankID)
                .reference(reference)
                .agreement(endUserAgreement.getId())
                .userLanguage("PL")

                // Optional parameters
                .ssn("")
                .accountSelection(false)
                .redirectImmediate(false);

        var spectacularRequisition = requisitionsService.createRequisition(requisitionRequest);

        var requisitionsID = spectacularRequisition.getId();
        mapReferenceRequisitionsID.put(reference, requisitionsID);

        return spectacularRequisition.getLink();
    }

    @Override
    public CustomerData getInfoAboutConection(String reference) {

        var requisitionsID = mapReferenceRequisitionsID.get(reference);
        var requisition = requisitionsService.getRequisition(requisitionsID).orElseThrow();
        var endUserAgreement = agreementsService.getAgreement(requisition.getAgreement()).orElseThrow();

        return CustomerDataMapper.fromDTO(requisition, endUserAgreement);    
    }
    
}
