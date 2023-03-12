package dj.services.integration.agreements;

import java.util.List;

import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;
import nordigen.SpectacularJWTObtain;

@Service
@RequiredArgsConstructor
public class AgreementsServiceImpl implements AgreementsService {

    private final TokenService tokenService;
    private final AgreementsClient agreementsClient;

    @Override
    public EndUserAgreement createAgreement(String institutionId) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        var endUserAgreementRequest = new EndUserAgreementRequest()
                .institutionId(institutionId)
                .maxHistoricalDays(90)
                .accessValidForDays(30)
                .accessScope(List.of("balances", "details", "transactions"));

        return agreementsClient.createAgreement(accessToken, endUserAgreementRequest);
    }

    @Override
    public EndUserAgreement getAgreement(String agreementID) {
        SpectacularJWTObtain tokens = tokenService.getTokens();
        String accessToken = "Bearer " + tokens.getAccess();

        return agreementsClient.getAgreement(accessToken, agreementID);
    }

}
