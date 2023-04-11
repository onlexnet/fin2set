package onlexnet.fin2set.nordigen.requistions;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import onlexnet.fin2set.nordigen.token.TokenService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.models.PaginatedRequisitionList;
import onlexnet.fin2set.domain.models.Requisition;
import onlexnet.fin2set.domain.models.SpectacularRequisition;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;
import onlexnet.fin2set.nordigen.mappers.PaginatedRequisitionListMapper;
import onlexnet.fin2set.nordigen.mappers.RequisitionMapper;
import onlexnet.fin2set.nordigen.mappers.SpectacularRequisitionMapper;

@Service
@RequiredArgsConstructor
public class RequisitionsServiceImpl implements RequisitionsService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;

    @Override
    public PaginatedRequisitionList getListAllRequisitions() {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = requisitionsClient.getListAllRequisitions(accessToken);
        return PaginatedRequisitionListMapper.fromDTO(response.getBody());
    }

    @Override
    public SpectacularRequisition createRequisition(RequisitionRequest requisitionRequest) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = requisitionsClient.createRequisition(accessToken, requisitionRequest);
        return SpectacularRequisitionMapper.fromDTO(response.getBody());
    }

    @Override
    public Optional<Requisition> getRequisition(UUID requisitionsID) {
        String accessToken = tokenService.buildBearerAuthToken();
        try {
            var response = requisitionsClient.getRequisition(accessToken, requisitionsID);
            return Optional.of(RequisitionMapper.fromDTO(response.getBody()));
        } catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                return Optional.empty();
            }
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void deleteRequsition(UUID requisitionsID) {
        String accessToken = tokenService.buildBearerAuthToken();
        requisitionsClient.deleteRequsition(accessToken, requisitionsID);
    }

    @Override
    public CreateRequisitionResult createRequisition(URI webhookAddress, String bankID, String myReference, UUID agreementId) {
        var requisitionRequest = new RequisitionRequest()
            .redirect(webhookAddress)
            .institutionId(bankID)
            .reference(myReference)
            .agreement(agreementId)
            .userLanguage("PL")

            // Optional parameters
            .ssn("")
            .accountSelection(false)
            .redirectImmediate(false);

        String accessToken = tokenService.buildBearerAuthToken();
        var response = requisitionsClient.createRequisition(accessToken, requisitionRequest);
        var response2 = SpectacularRequisitionMapper.fromDTO(response.getBody());
        return CreateRequisitionResult.of(response2.getId(), response2.getLink());
    }

}