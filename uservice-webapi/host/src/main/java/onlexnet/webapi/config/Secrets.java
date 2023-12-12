package onlexnet.webapi.config;

import org.springframework.stereotype.Component;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import jakarta.annotation.PostConstruct;

/**
 * In 'standard' approach to configure nowadays applications, all values are available as environment variables.
 * In DAPR we have to ask all variables / secrets in interactive way as it is not able to inject such variables to the application
 */
@Component
public class Secrets implements AutoCloseable {
  
  private static final String SECRET_STORE_NAME = "azurekeyvault";

  private static final String OPENAI_KEY_NAME = "OPENAI-KEY";
  private static final String OPENAI_ENDPOINT_NAME = "OPENAI-ENDPOINT";

  public String openaiKey;
  public String openaiEndpoint;

  private DaprClient client;

  int daprAgentPort;

  @PostConstruct
  void init() {
    client = new DaprClientBuilder()
        .build();
    client.waitForSidecar(3_000).block();
      
    //Using Dapr SDK to get a secret
    var openaiKeyMap = client.getSecret(SECRET_STORE_NAME, OPENAI_KEY_NAME).block();
    var openaiEndpointMap = client.getSecret(SECRET_STORE_NAME, OPENAI_ENDPOINT_NAME).block();

    openaiKey = openaiKeyMap.get(OPENAI_KEY_NAME);
    openaiEndpoint = openaiEndpointMap.get(OPENAI_ENDPOINT_NAME);
  }

  @Override
  public void close() throws Exception {
    client.close();
  }
}
