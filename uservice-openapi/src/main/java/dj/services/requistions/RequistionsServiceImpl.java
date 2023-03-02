package dj.services.requistions;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dj.dto.integration.AgreementData;
import dj.dto.integration.build_a_link.DataForCreateConnection;
import dj.dto.integration.build_a_link.ResponseEndingIntegration;
import dj.services.agreements.AgreementsService;
import dj.services.token.TokenService;
import lombok.RequiredArgsConstructor;
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

        AgreementData agreementData = agreementsService.createAgreement(institutionId);

        DataForCreateConnection dataForCreateConnection = new DataForCreateConnection()
                .setRedirect("http://localhost:8080/swagger-ui.html#/integration")
                .setInstitutionId(institutionId)
                .setReference(UUID.randomUUID().toString())
                .setAgreement(agreementData.getId())
                .setUserLanguage("PL");

        ResponseEndingIntegration responseEndingIntegration = requistionsClient.createConnection(accessToken, dataForCreateConnection);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(responseEndingIntegration.getLink())).build();
    }
    
}
