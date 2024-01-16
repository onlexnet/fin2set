package onlexnet.webapi.infra.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import onlexnet.webapi.plaid.PlaidConnection;

// @Component
@RequiredArgsConstructor
class PlaidConnectionHealth implements HealthIndicator {
  
  private final PlaidConnection connection;

  @Override
  public Health health() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'health'");
  }

}
