package onlexnet.webapi.ports.plaid;

import org.springframework.stereotype.Component;

import onlexnet.webapi.ports.PlaidConnection;

@Component
class PlaidConnectionImpl implements PlaidConnection {

  @Override
  public boolean isWorking() {
    return false;
  }
  
}
