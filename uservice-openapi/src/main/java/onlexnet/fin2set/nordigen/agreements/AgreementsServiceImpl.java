package onlexnet.fin2set.nordigen.agreements;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import onlexnet.fin2set.nordigen.token.TokenService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.models.EndUserAgreement;
import onlexnet.fin2set.domain.models.PaginatedEndUserAgreementList;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;
import onlexnet.fin2set.nordigen.mappers.EndUserAgreementMapper;
import onlexnet.fin2set.nordigen.mappers.PaginatedEndUserAgreementsListMapper;

@Service
@RequiredArgsConstructor
public class AgreementsServiceImpl implements AgreementsService {

    private final TokenService tokenService;
    private final AgreementsClient agreementsClient;

    @Override
    public EndUserAgreement createAgreement(EndUserAgreementRequest endUserAgreementRequest) {
        var accessToken = tokenService.buildBearerAuthToken();
        var response = agreementsClient.createAgreement(accessToken, endUserAgreementRequest);
        return EndUserAgreementMapper.fromDTO(response.getBody());
    }

    @Override
    public UUID createAgreement(String bankID, int maxHistoricalDays, int accessValidForDays) {
        var request = new EndUserAgreementRequest()
            .institutionId(bankID)
            .maxHistoricalDays(maxHistoricalDays)
            .accessValidForDays(accessValidForDays)
            .accessScope(List.of("balances", "details", "transactions"));  
        var accessToken = tokenService.buildBearerAuthToken();
        var response = agreementsClient.createAgreement(accessToken, request);
        return EndUserAgreementMapper.fromDTO(response.getBody()).getId();
    }

    @Override
    public Optional<EndUserAgreement> getAgreement(UUID agreementID) {
        var accessToken = tokenService.buildBearerAuthToken();
        try {
            var response = agreementsClient.getAgreement(accessToken, agreementID);
            var domain = EndUserAgreementMapper.fromDTO(response.getBody());
            return Optional.of(domain);
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
        var response = agreementsClient.getListAllAgreements(accessToken);
        return PaginatedEndUserAgreementsListMapper.fromDTO(response.getBody());
    }

    @Override
    public void deleteAgreement(UUID agreementID) {
        String accessToken = tokenService.buildBearerAuthToken();
        agreementsClient.deleteAgreement(accessToken, agreementID);
    }

}
