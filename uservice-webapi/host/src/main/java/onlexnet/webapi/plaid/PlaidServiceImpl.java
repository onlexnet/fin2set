package onlexnet.webapi.plaid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.plaid.client.ApiClient;
import com.plaid.client.model.CountryCode;
import com.plaid.client.model.ItemPublicTokenExchangeRequest;
import com.plaid.client.model.LinkTokenCreateRequest;
import com.plaid.client.model.LinkTokenCreateRequestUser;
import com.plaid.client.model.Products;
import com.plaid.client.request.PlaidApi;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
class PlaidServiceImpl implements PlaidConnection, PlaidService {

  private final PlaidProperties plaidProperties;

  private PlaidApi plaidClient;

  @PostConstruct
  void init() {
    var apiKeys = new HashMap<String, String>();
    apiKeys.put("clientId", plaidProperties.plaidClientId());
    apiKeys.put("secret", plaidProperties.plaidSecret());
    var apiClient = new ApiClient(apiKeys);
    apiClient.setPlaidAdapter(ApiClient.Sandbox);
    plaidClient = apiClient.createService(PlaidApi.class);
  }

  @Override
  public boolean isWorking() {
    return false;
  }

  @Override
  public void doSomething() {
  }

  @Override
  @SneakyThrows
  public String createLinkToken() {
    var clientUserId = UUID.randomUUID().toString();

    var user = new LinkTokenCreateRequestUser()
        .clientUserId(clientUserId)
        .legalName("legal name")
        .phoneNumber("4155558888")
        .emailAddress("email@address.com");

    var request = new LinkTokenCreateRequest()
        .user(user)
        .clientName("Plaid Test App")
        .products(Arrays.asList(Products.TRANSACTIONS))
        .countryCodes(Arrays.asList(CountryCode.US))
        .language("pl")
        .redirectUri("http://localhost:3000")
        // .webhook("https://example.com/webhook")
        .linkCustomizationName("default");

    var response1 = plaidClient
        .linkTokenCreate(request)
        .execute();

    var h = response1.headers();
    log.info(h.toString());    
    var linkToken = response1.body().getLinkToken();

    return linkToken;

  }

  // Synchronously exchange a Link public_token for an API access_token
  @Override
  @SneakyThrows
  public String exchangeLinkToken(String linkToken) {
    var request = new ItemPublicTokenExchangeRequest().publicToken(linkToken);
    var response = plaidClient.itemPublicTokenExchange(request).execute();
    String accessToken;
    if (response.isSuccessful()) {
      accessToken = response.body().getAccessToken();
      return accessToken;
    } else {
      var err = response.errorBody().string();
      throw new IllegalArgumentException(err);
    }
  }

}
