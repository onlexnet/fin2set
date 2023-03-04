package dj.services.requistions;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dj.services.agreements.AgreementsService;
import dj.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularJWTObtain;

@Service
@RequiredArgsConstructor
public class RequisitionsServiceImpl implements RequisitionsService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;
    private final AgreementsService agreementsService;

    Map<String, String> mapReferenceRequisitionsID = new HashMap<>();

    @Override
    public URI createConnection(String institutionId) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        var endUserAgreement = agreementsService.createAgreement(institutionId);

        var reference = UUID.randomUUID().toString();

        var requisitionV2Request = new RequisitionV2Request()
                .redirect("http://localhost:8080/api/integration/move")
                .institutionId(institutionId)
                .reference(reference)
                .agreement(endUserAgreement.getId())
                .userLanguage("PL")

                // Optional parameters
                .ssn("")
                .accountSelection(false)
                .redirectImmediate(false);

        var spectacularRequisitionV2 = requisitionsClient.createConnection(accessToken, requisitionV2Request);

        return URI.create(spectacularRequisitionV2.getLink());
    }

    @Override
    public String getListAccounts(String reference) {
        return "https://ob.nordigen.com/api/v2/requisitions/";
    }

}
