package onlexnet.fin2set.nordigen.service.functionality;

import java.util.UUID;

import onlexnet.fin2set.domain.models.BankUserDetailsConnection;

public interface BankUserDetailsConnectionService {
  
  BankUserDetailsConnection getBankUserDetailsConnection(UUID connectionID);
}
