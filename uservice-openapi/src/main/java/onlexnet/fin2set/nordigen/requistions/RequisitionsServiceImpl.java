package onlexnet.fin2set.nordigen.requistions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import onlexnet.fin2set.nordigen.token.TokenService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.api.dto.PaginatedRequisitionListDTO;
import onlexnet.fin2set.api.dto.PaginatedRequisitionListMapper;
import onlexnet.fin2set.api.dto.RequisitionDTO;
import onlexnet.fin2set.api.dto.RequisitionMapper;
import onlexnet.fin2set.api.dto.SpectacularRequisitionDTO;
import onlexnet.fin2set.api.dto.SpectacularRequisitionMapper;
import onlexnet.fin2set.nordigen.generated.RequisitionRequest;

@Service
@RequiredArgsConstructor
public class RequisitionsServiceImpl implements RequisitionsService {

    private final TokenService tokenService;
    private final RequisitionsClient requisitionsClient;
    private final PaginatedRequisitionListMapper paginatedRequisitionListMapper;
    private final RequisitionMapper requisitionMapper;
    private final SpectacularRequisitionMapper spectacularRequisitionMapper;

    @Override
    public PaginatedRequisitionListDTO getListAllRequisitions() {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = requisitionsClient.getListAllRequisitions(accessToken);
        return paginatedRequisitionListMapper.toDTO(response);
    }

    @Override
    public SpectacularRequisitionDTO createRequisition(RequisitionRequest requisitionRequest) {
        String accessToken = tokenService.buildBearerAuthToken();
        var response = requisitionsClient.createRequisition(accessToken, requisitionRequest);
        return spectacularRequisitionMapper.toDTO(response);
    }

    @Override
    public Optional<RequisitionDTO> getRequisition(UUID requisitionsID) {
        String accessToken = tokenService.buildBearerAuthToken();
        try {
            var response = requisitionsClient.getRequisition(accessToken, requisitionsID);
            return Optional.of(requisitionMapper.toDTO(response));
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
