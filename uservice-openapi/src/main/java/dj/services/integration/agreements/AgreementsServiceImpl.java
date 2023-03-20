package dj.services.integration.agreements;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
import feign.FeignException;
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
    public Optional<EndUserAgreement> getAgreement(String agreementID) {
        String accessToken = tokenService.buildBearerAuthToken();
        try {
            var httpResult = agreementsClient.getAgreement(accessToken, agreementID);
            return Optional.of(httpResult);
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                return Optional.empty();
            }
            throw new IllegalStateException(ex);
        }
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
