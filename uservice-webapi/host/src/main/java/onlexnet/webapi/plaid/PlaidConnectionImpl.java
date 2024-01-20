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
class PlaidConnectionImpl implements PlaidConnection {

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
  @SneakyThrows
  public void doSomething() {
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


    // Synchronously exchange a Link public_token for an API access_token
    // Required request parameters are always Request object constructor arguments
    var request1 = new ItemPublicTokenExchangeRequest()
        .publicToken(linkToken);
    var response = plaidClient.itemPublicTokenExchange(request1).execute();
    String accessToken;
    if (response.isSuccessful()) {
      accessToken = response.body().getAccessToken();
    } else {
      var err = response.errorBody().string();
      throw new IllegalArgumentException(err);
    }

    // // Decoding an unsuccessful response
    // try {
    // Gson gson = new Gson();
    // PlaidError error = gson.fromJson(response.errorBody().string(),
    // PlaidError.class);
    // } catch (Exception e) {
    // throw new Exception(
    // String.format(
    // "Failed converting from API Response Error Body to Error %f",
    // response.errorBody().string()
    // )
    // );
  }

}
