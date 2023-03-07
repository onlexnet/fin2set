package dj.services.requistions;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dj.services.agreements.AgreementsService;
import dj.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularJWTObtain;

@Service
@RequiredArgsConstructor
public class RequisitionsServiceImpl implements RequisitionsService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;
    private final AgreementsService agreementsService;

    private Map<String, String> mapReferenceRequisitionsID = new HashMap<>();

    @Override
    public URI createRequisition(String institutionId) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        var endUserAgreement = agreementsService.createAgreement(institutionId);

        var reference = UUID.randomUUID().toString();

        var requisitionV2Request = new RequisitionV2Request()
                .redirect("http://localhost:8080/api/integration/accounts")
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
    public RequisitionV2 getListAccounts(String reference) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();
        String requisitionsID = mapReferenceRequisitionsID.get(reference);
        return requisitionsClient.getRequisition(accessToken, requisitionsID);
    }

}
