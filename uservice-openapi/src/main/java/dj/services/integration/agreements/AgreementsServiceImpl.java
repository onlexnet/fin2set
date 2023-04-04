package dj.services.integration.agreements;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dj.models.dto.EndUserAgreementDTO;
import dj.models.dto.EndUserAgreementMapper;
import dj.models.dto.PaginatedEndUserAgreementListDTO;
import dj.models.dto.PaginatedEndUserAgreementsListMapper;
import dj.services.integration.token.TokenService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;

@Service
@RequiredArgsConstructor
public class AgreementsServiceImpl implements AgreementsService {

    private final TokenService tokenService;
    private final AgreementsClient agreementsClient;
    private final EndUserAgreementMapper endUserAgreementMapper;
    private final PaginatedEndUserAgreementsListMapper paginatedEndUserAgreementsListMapper;

    @Override
    public EndUserAgreementDTO createAgreement(EndUserAgreementRequest endUserAgreementRequest) {
        var accessToken = tokenService.buildBearerAuthToken();
        var response = agreementsClient.createAgreement(accessToken, endUserAgreementRequest);
        return endUserAgreementMapper.toDTO(response);
    }

    @Override
    public Optional<EndUserAgreementDTO> getAgreement(UUID agreementID) {
        String accessToken = tokenService.buildBearerAuthToken();
        try {
            var response = agreementsClient.getAgreement(accessToken, agreementID);
            var responseDTO = endUserAgreementMapper.toDTO(response);
            return Optional.of(responseDTO);
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                return Optional.empty();
            }
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public PaginatedEndUserAgreementListDTO getListAllAgreements() {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = agreementsClient.getListAllAgreements(accessToken);
        return paginatedEndUserAgreementsListMapper.toDTO(response);
    }

    @Override
    public void deleteAgreement(UUID agreementID) {
        String accessToken = tokenService.buildBearerAuthToken();
        agreementsClient.deleteAgreement(accessToken, agreementID);
    }

}
