package dj.services.integration.requistions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import nordigen.PaginatedRequisitionList;
import nordigen.Requisition;
import nordigen.RequisitionRequest;
import nordigen.SpectacularRequisition;

@Service
@RequiredArgsConstructor
public class RequisitionsServiceImpl implements RequisitionsService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;

    @Override
    public PaginatedRequisitionList getListAllRequisitions() {
        String accessToken = tokenService.buildBearerAuthToken();
        return requisitionsClient.getListAllRequisitions(accessToken);
    }

    @Override
    public SpectacularRequisition createRequisition(RequisitionRequest RequisitionRequest) {
        String accessToken = tokenService.buildBearerAuthToken();
        return requisitionsClient.createRequisition(accessToken, RequisitionRequest);
    }

    @Override
    public Optional<Requisition> getRequisition(UUID requisitionsID) {
        String accessToken = tokenService.buildBearerAuthToken();
        try {
            var httpResult = requisitionsClient.getRequisition(accessToken, requisitionsID);
            return Optional.of(httpResult);
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

}
