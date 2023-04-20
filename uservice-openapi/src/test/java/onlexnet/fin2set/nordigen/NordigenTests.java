package onlexnet.fin2set.nordigen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import onlexnet.fin2set.db.DbExtension;
import onlexnet.fin2set.nordigen.integration.IntegrationService;
import onlexnet.fin2set.nordigen.requistions.RequisitionsService;

@SpringBootTest
@ExtendWith(DbExtension.class)
@ActiveProfiles("test")
public class NordigenTests {
  
  @Autowired
  IntegrationService integrationService;
  
  @Autowired
  RequisitionsService requisitionsService;

  @Test
  void shouldLoadDataFromTheBank() {
    var banks = integrationService.getListBanks("PL");
    // requisitionsService.createRequisition(null, null, null, null)
  }
}
