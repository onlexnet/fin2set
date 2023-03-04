package dj.services.requistions;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dj.services.agreements.AgreementsService;
import dj.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularJWTObtain;

@Service
@RequiredArgsConstructor
public class RequistionsServiceImpl implements RequistionsService{

    private final TokenService tokenService;
    private final RequistionsClient requistionsClient;
    private final AgreementsService agreementsService;

    @Override
    public ResponseEntity<?> createConnection(String institutionId) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        var endUserAgreement = agreementsService.createAgreement(institutionId);

        var reference = UUID.randomUUID().toString();

        var requisitionV2Request = new RequisitionV2Request()
        .redirect("http://localhost:8080/api/integration/move/" + reference)
        .institutionId(institutionId)
        .reference(reference)
        .agreement(endUserAgreement.getId())
        .userLanguage("PL")
        
        // Sławek co z tym zrobić
        .ssn("1")
        .accountSelection(false)
        .redirectImmediate(false);
        
        var spectacularRequisitionV2 = requistionsClient.createConnection(accessToken, requisitionV2Request);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(spectacularRequisitionV2.getLink())).build();
    }

    @Override
    public String getListAccounts(String reference) {
        return "https://ob.nordigen.com/api/v2/requisitions/";
    }
    
}
