package dj.services.integration.agreements;

import java.util.List;

import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;
import nordigen.PaginatedEndUserAgreementList;

@Service
@RequiredArgsConstructor
public class AgreementsServiceImpl implements AgreementsService {

    private final TokenService tokenService;
    private final AgreementsClient agreementsClient;

    @Override
    public EndUserAgreement createAgreement(EndUserAgreementRequest endUserAgreementRequest) {
        String accessToken = tokenService.buildBearerAuthToken();

        return agreementsClient.createAgreement(accessToken, endUserAgreementRequest);
    }

    @Override
    public EndUserAgreement getAgreement(String agreementID) {
        String accessToken = tokenService.buildBearerAuthToken();

        return agreementsClient.getAgreement(accessToken, agreementID);
    }

    @Override
    public PaginatedEndUserAgreementList getListAllAgreements() {
        String accessToken = tokenService.buildBearerAuthToken();
        return agreementsClient.getListAllAgreements(accessToken);
    }

    @Override
    public void deleteAgreement(String agreementID) {
        String accessToken = tokenService.buildBearerAuthToken();
        agreementsClient.deleteAgreement(accessToken, agreementID);
    }

}
