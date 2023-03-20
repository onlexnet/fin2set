package dj.services.integration.requistions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import nordigen.PaginatedRequisitionV2List;
import nordigen.RequisitionV2;
import nordigen.RequisitionV2Request;
import nordigen.SpectacularRequisitionV2;

@Service
@RequiredArgsConstructor
public class RequisitionsServiceImpl implements RequisitionsService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;

    @Override
    public PaginatedRequisitionV2List getListAllRequisitions() {
        String accessToken = tokenService.buildBearerAuthToken();
        return requisitionsClient.getListAllRequisitions(accessToken);
    }

    @Override
    public SpectacularRequisitionV2 createRequisition(RequisitionV2Request requisitionV2Request) {
        String accessToken = tokenService.buildBearerAuthToken();
        return requisitionsClient.createRequisition(accessToken, requisitionV2Request);
    }

    @Override
    public Optional<RequisitionV2> getRequisition(UUID requisitionsID) {
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
