package onlexnet.fin2set.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.bankstatement.BankStatementService;
import onlexnet.fin2set.domain.connect.ConnectService;
import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.generated.api.ApiApiDelegate;
import onlexnet.fin2set.generated.dto.IntegrationDTO;
import onlexnet.fin2set.nordigen.integration.IntegrationService;

@Component
@RequiredArgsConstructor
class AppApi implements ApiApiDelegate {

  private final ConnectService connectService;
  private final BankStatementService onlexService;
  private final IntegrationService integrationService;

  @Override
  public ResponseEntity<List<IntegrationDTO>> getBanks(String country) {
    var result = integrationService.getListBanks(country);
    var asDto = result.stream().map(it -> {
      var dto = new IntegrationDTO();
      dto.setName(it.getName());
      return dto;
    }).toList();
    return ResponseEntity.ok(asDto);
  }


}
