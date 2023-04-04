package onlexnet.fin2set.domain.connect;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.domain.models.CustomerDataDTO;
import onlexnet.fin2set.domain.models.CustomerDataMapper;
import onlexnet.fin2set.nordigen.agreements.AgreementsService;
import onlexnet.fin2set.nordigen.requistions.RequisitionsClient;
import onlexnet.fin2set.nordigen.token.TokenService;
import lombok.AllArgsConstructor;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;

@Service
@AllArgsConstructor
public class ConnectServiceImpl implements ConnectService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;
    private final AgreementsService agreementsService;
    private final CustomerDataMapper customerDataMapper;

    private Map<String, UUID> mapReferenceRequisitionsID = new HashMap<>();

    @Override
    public URI createLinkToConnect(String institutionId) {
        String accessToken = "Bearer " + tokenService.getTokens().getAccess();

        var endUserAgreementRequest = new EndUserAgreementRequest()
                .institutionId(institutionId)
                .maxHistoricalDays(90)
                .accessValidForDays(30)
                .accessScope(List.of("balances", "details", "transactions"));  

        var endUserAgreement = agreementsService.createAgreement(endUserAgreementRequest);

        var reference = UUID.randomUUID().toString();

        var RequisitionRequest = new RequisitionRequest()
                .redirect(URI.create("http://localhost:8080/api/integration/info"))
                .institutionId(institutionId)
                .reference(reference)
                .agreement(endUserAgreement.getId())
                .userLanguage("PL")

                // Optional parameters
                .ssn("")
                .accountSelection(false)
                .redirectImmediate(false);

        var SpectacularRequisition = requisitionsClient.createRequisition(accessToken, RequisitionRequest);

        var requisitionsID = SpectacularRequisition.getId();
        mapReferenceRequisitionsID.put(reference, requisitionsID);

        return SpectacularRequisition.getLink();
    }

    @Override
    public CustomerDataDTO getInfoAboutConection(String reference) {
        String accessToken = "Bearer " + tokenService.getTokens().getAccess();

        var requisitionsID = mapReferenceRequisitionsID.get(reference);
        var requisition = requisitionsClient.getRequisition(accessToken, requisitionsID);
        var maybeEndUserAgreement = agreementsService.getAgreement(requisition.getAgreement());

        return maybeEndUserAgreement.map(it -> customerDataMapper.toDto(requisition, it))
            .orElseThrow();
    }
    
}
