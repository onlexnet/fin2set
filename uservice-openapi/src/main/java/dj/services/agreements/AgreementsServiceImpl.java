package dj.services.agreements;

import java.util.List;

import org.springframework.stereotype.Service;

import dj.dto.integration.AgreementData;
import dj.dto.integration.IntegrationForm;
import dj.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.SpectacularJWTObtain;

@Service
@RequiredArgsConstructor
public class AgreementsServiceImpl implements AgreementsService {

    private final TokenService tokenService;
    private final AgreementsClient agreementsClient;

    // We will not use default settings for user agreement and set our own, need only to user give us bank id which have to be connected
    // setMaxHistoricalDays = 90
    // setAccessValidForDays = 30
    // setAccessScope = "balances", "details", "transactions"
    @Override
    public AgreementData createAgreement(String institutionId) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        IntegrationForm integrationForm = new IntegrationForm()
                .setInstitutionId(institutionId)
                .setMaxHistoricalDays(90)
                .setAccessValidForDays(30)
                .setAccessScope(List.of("balances", "details", "transactions"));

        return agreementsClient.createAgreement(accessToken, integrationForm);
    }

    
}
