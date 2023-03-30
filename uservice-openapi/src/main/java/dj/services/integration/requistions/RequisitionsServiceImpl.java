package dj.services.integration.requistions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dj.models.dto.PaginatedRequisitionListDTO;
import dj.models.dto.PaginatedRequisitionListMapper;
import dj.models.dto.RequisitionDTO;
import dj.models.dto.RequisitionMapper;
import dj.models.dto.SpectacularRequisitionDTO;
import dj.models.dto.SpectacularRequisitionMapper;
import dj.services.integration.token.TokenService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import nordigen.RequisitionRequest;

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
