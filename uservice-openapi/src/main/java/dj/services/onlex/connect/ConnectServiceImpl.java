package dj.services.onlex.connect;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dj.models.CustomerDataDTO;
import dj.models.CustomerDataMapper;
import dj.services.integration.agreements.AgreementsService;
import dj.services.integration.requistions.RequisitionsClient;
import dj.services.integration.token.TokenService;
import lombok.AllArgsConstructor;
import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;

@Service
@AllArgsConstructor
public class ConnectServiceImpl implements ConnectService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;
    private final AgreementsService agreementsService;
    private final CustomerDataMapper customerDataMapper;

    private Map<String, String> mapReferenceRequisitionsID = new HashMap<>();

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

        var requisitionV2Request = new RequisitionV2Request()
                .redirect("http://localhost:8080/api/integration/info")
                .institutionId(institutionId)
                .reference(reference)
                .agreement(endUserAgreement.getId())
                .userLanguage("PL")

                // Optional parameters
                .ssn("")
                .accountSelection(false)
                .redirectImmediate(false);

        var spectacularRequisitionV2 = requisitionsClient.createRequisition(accessToken, requisitionV2Request);

        String requisitionsID = spectacularRequisitionV2.getId().toString();
        mapReferenceRequisitionsID.put(reference, requisitionsID);

        return URI.create(spectacularRequisitionV2.getLink());
    }

    @Override
    public CustomerDataDTO getInfoAboutConection(String reference) {
        String accessToken = "Bearer " + tokenService.getTokens().getAccess();

        String requisitionsID = mapReferenceRequisitionsID.get(reference);
        RequisitionV2 requisition = requisitionsClient.getRequisition(accessToken, requisitionsID);
        var maybeEndUserAgreement = agreementsService.getAgreement(requisition.getAgreement().toString());

        return maybeEndUserAgreement.map(it -> customerDataMapper.toDto(requisition, it))
            .orElseThrow();
    }
    
}
