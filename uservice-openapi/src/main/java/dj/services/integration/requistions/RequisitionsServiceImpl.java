package dj.services.integration.requistions;

import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
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
    public RequisitionV2 getRequisition(String requisitionsID) {
        String accessToken = tokenService.buildBearerAuthToken();
        return requisitionsClient.getRequisition(accessToken, requisitionsID);
    }

    @Override
    public void deleteRequsition() {
        String accessToken = tokenService.buildBearerAuthToken();
        requisitionsClient.deleteRequsition(accessToken, accessToken);
    }

}
