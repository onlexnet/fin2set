package onlexnet.fin2set.api;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.api.mappers.BankMapper;
import onlexnet.fin2set.api.mappers.BankStatementMapper;
import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;
import onlexnet.fin2set.generated.api.ApiApiDelegate;
import onlexnet.fin2set.host.AuthProvider;
import onlexnet.fin2set.nordigen.NordigenFacade;

@Component
@RequiredArgsConstructor
final class ApiDelegate implements ApiApiDelegate {

  private final AuthProvider ap;
  private final NordigenFacade nordigenFacade;

  @Override
  public ResponseEntity<String> whoAmI() {
    return ResponseEntity.ok(ap.current().toString());
  }

  @Override
  public ResponseEntity<List<onlexnet.fin2set.generated.dto.Bank>> getListBanks(String country) {
    return ResponseEntity.ok(BankMapper.toDTO(nordigenFacade.getListBanks(country)));
  }

  @Override
  public ResponseEntity<onlexnet.fin2set.generated.dto.Bank> getBank(String bankID) {
    return ResponseEntity.ok(BankMapper.toDTO(nordigenFacade.getBank(bankID)));
  }

  @Override
  public ResponseEntity<URI> generateUriToConnectionWithBankAccount(String bankID) {
    return ResponseEntity.ok(nordigenFacade.createLinkToConnect(bankID));
  }

  @Override
  public ResponseEntity<BankUserDetailsConnection> catchUser(String reference) {
    return ResponseEntity.ok(nordigenFacade.catchUserAndGetInfoAboutConection(reference));
  }

  @Override
  public ResponseEntity<BankUserDetailsConnection> getInfoAboutUserConnection(String connectionID) {
    return ResponseEntity.ok(nordigenFacade.catchUserAndGetInfoAboutConection(connectionID));
  }

  @Override
  public ResponseEntity<onlexnet.fin2set.generated.dto.BankStatement> getBankstatement(UUID accountID) {
    return ResponseEntity.ok(BankStatementMapper.toDTO(nordigenFacade.getBankStatement(accountID)));
  }

}
