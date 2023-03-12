package dj.services.integration.requistions;

import org.springframework.stereotype.Service;

import dj.services.integration.token.TokenService;
import lombok.RequiredArgsConstructor;
import nordigen.PaginatedRequisitionV2List;

@Service
@RequiredArgsConstructor
public class RequisitionsServiceImpl implements RequisitionsService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;

    @Override
    public PaginatedRequisitionV2List getListAllRequisitions() {
        String accessToken = "Bearer " + tokenService.getTokens().getAccess();
        return requisitionsClient.getListAllRequisitions(accessToken);
    }

}
