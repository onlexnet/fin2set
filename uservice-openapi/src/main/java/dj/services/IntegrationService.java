package dj.services;

import dj.dto.integration.AgreementData;
import dj.dto.integration.IntegrationForm;
import dj.dto.integration.bank.Bank;
import dj.dto.integration.build_a_link.DataForCreateConnection;
import dj.dto.integration.build_a_link.ResponseEndingIntegration;
import dj.dto.integration.secrets.Secrets;
import dj.dto.integration.secrets.token.Tokens;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntegrationService {

    @Value("${NORDIGEN_SECRET_KEY}")
    private String secretKey;

    @Value("${NORDIGEN_SECRET_ID}")
    private String secretId;

    private final IntegrationClient integrationClient;

    private Tokens getTokens() {
        Secrets secrets = new Secrets()
                .setSecretKey(secretKey)
                .setSecretId(secretId);
        return integrationClient.createTokens(secrets);
    }

    public List<Bank> getListBanks(String country) {
        Tokens tokens = getTokens();
        String accessToken = "Bearer " + tokens.getAccess();
        return integrationClient.getBankList(accessToken, country);
    }

    // We will not use default settings for user agreement and set our own, need only to user give us bank id which have to be connected
    // setMaxHistoricalDays = 90
    // setAccessValidForDays = 30
    // setAccessScope = "balances", "details", "transactions"
    public AgreementData createAgreement(String institutionId) {

        Tokens tokens = getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        IntegrationForm integrationForm = new IntegrationForm()
                .setInstitutionId(institutionId)
                .setMaxHistoricalDays(90)
                .setAccessValidForDays(30)
                .setAccessScope(List.of("balances", "details", "transactions"));

        return integrationClient.createAgreement(accessToken, integrationForm);
    }

    // This method set us user agreements and transfers the customer to the bank's website in order to authorize the provision of his bank details to us

    public ResponseEntity<?> createConnection(String institutionId) {

        Tokens tokens = getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        AgreementData agreementData = createAgreement(institutionId);

        DataForCreateConnection dataForCreateConnection = new DataForCreateConnection()
                .setRedirect("http://localhost:8080/swagger-ui.html#/integration")
                .setInstitutionId(institutionId)
                .setReference(UUID.randomUUID().toString())
                .setAgreement(agreementData.getId())
                .setUserLanguage("PL");

        ResponseEndingIntegration responseEndingIntegration = integrationClient.createConnection(accessToken, dataForCreateConnection);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(responseEndingIntegration.getLink())).build();
    }
}
