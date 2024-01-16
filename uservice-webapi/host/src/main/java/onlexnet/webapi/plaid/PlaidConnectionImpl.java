package onlexnet.webapi.plaid;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.plaid.client.ApiClient;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class PlaidConnectionImpl implements PlaidConnection {

  private final PlaidProperties plaidProperties;

  @PostConstruct
  void init() {
      var apiKeys = new HashMap<String, String>();
      apiKeys.put("clientId", plaidProperties.plaidClientId());
      apiKeys.put("secret", plaidProperties.plaidSecret());
      var apiClient = new ApiClient(apiKeys);
      apiClient.setPlaidAdapter(ApiClient.Sandbox);
  }

  @Override
  public boolean isWorking() {
    return false;
  }
  
}
