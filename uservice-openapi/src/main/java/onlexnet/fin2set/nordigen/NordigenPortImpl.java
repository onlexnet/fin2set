package onlexnet.fin2set.nordigen;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;
import onlexnet.fin2set.nordigen.integration.accounts.AccountService;
import onlexnet.fin2set.nordigen.integration.agreements.AgreementsService;
import onlexnet.fin2set.nordigen.integration.institutions.InstitutionsService;
import onlexnet.fin2set.nordigen.integration.requistions.RequisitionsService;
import onlexnet.fin2set.nordigen.models.mappers.BankMapper;
import onlexnet.fin2set.nordigen.models.mappers.BankStatemantMapper;
import onlexnet.fin2set.nordigen.models.mappers.BankUserDetailsConnectionMapper;

@Service
@RequiredArgsConstructor
public class NordigenPortImpl implements NordigenPort {

  private final RequisitionsService requisitionsService;
  private final AgreementsService agreementsService;
  private final InstitutionsService institutionsService;
  private final AccountService accountService;

  // TODO 1 - create concurrent map
  // TODO 2 - move to distributed cache
  // TODO 3 - support configurable timeout
  private Map<String, UUID> mapReferenceRequisitionsID = new HashMap<>();

  @Override
  public URI createLinkToConnect(String bankID) {

    final var maxHistoricalDays = 90;
    final var accessValidForDays = 30;

    var agreementId = agreementsService.createAgreement(bankID, maxHistoricalDays, accessValidForDays);

    var myReference = UUID.randomUUID().toString();

    // TODO - address should be configured as hosted protocol, address and port will
    // be different per environment
    var webhookAddress = URI.create("http://192.168.0.100:8080/api/integration/info");

    var requisitionsResult = requisitionsService.createRequisition(webhookAddress, bankID, myReference, agreementId);
    mapReferenceRequisitionsID.put(myReference, requisitionsResult.getId());

    return requisitionsResult.getContinuationLink();
  }

  @Override
  public BankUserDetailsConnection getInfoAboutConection(String reference) {

    var requisitionsID = mapReferenceRequisitionsID.get(reference);
    var requisition = requisitionsService.getRequisition(requisitionsID).orElseThrow();
    var endUserAgreement = agreementsService.getAgreement(requisition.getAgreement()).orElseThrow();

    return BankUserDetailsConnectionMapper.fromDTO(requisition, endUserAgreement);
  }

  @Override
  public List<Bank> getListBanks(String country) {
    var banks = institutionsService.getListBanks(country);
    return BankMapper.fromDTO(banks);
  }

  @Override
  public Bank getBank(String bankID) {
    var bank = institutionsService.getBank(bankID);
    return BankMapper.fromDTO(bank);
  }

  @Override
  public BankStatement getBankStatement(UUID accountID) {
    var nordigenBankStatemant = accountService.getTransactions(accountID);
    var account = accountService.getAccount(accountID);
    return BankStatemantMapper.fromDTO(nordigenBankStatemant, account);
  }

  @Override
  public BankUserDetailsConnection getBankUserDetailsConnection(UUID connectionID) {

        var requisition = requisitionsService.getRequisition(connectionID).orElseThrow();
        var endUserAgreement = agreementsService.getAgreement(requisition.getAgreement()).orElseThrow();

        return BankUserDetailsConnectionMapper.fromDTO(requisition, endUserAgreement);   
  }


}
