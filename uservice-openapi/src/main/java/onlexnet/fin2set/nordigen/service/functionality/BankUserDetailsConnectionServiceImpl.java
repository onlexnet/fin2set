package onlexnet.fin2set.nordigen.service.functionality;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;
import onlexnet.fin2set.nordigen.integration.agreements.AgreementsService;
import onlexnet.fin2set.nordigen.integration.requistions.RequisitionsService;
import onlexnet.fin2set.nordigen.models.mappers.BankUserDetailsConnectionMapper;

@Service
@RequiredArgsConstructor
public class BankUserDetailsConnectionServiceImpl implements BankUserDetailsConnectionService {

  private final RequisitionsService requisitionsService;
  private final AgreementsService agreementsService;

  @Override
  public BankUserDetailsConnection getBankUserDetailsConnection(UUID connectionID) {

        var requisition = requisitionsService.getRequisition(connectionID).orElseThrow();
        var endUserAgreement = agreementsService.getAgreement(requisition.getAgreement()).orElseThrow();

        return BankUserDetailsConnectionMapper.fromDTO(requisition, endUserAgreement);   
  }

}
