package onlexnet.webapi.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dapr.client.DaprClientBuilder;
import jakarta.annotation.PostConstruct;

@Component
public class Secrets {
  
  private static final ObjectMapper JSON_SERIALIZER = new ObjectMapper();
  private static final String SECRET_STORE_NAME = "localsecretstore";

  private static final String OPENAI_KEY_NAME = "OPENAI_KEY";
  private static final String OPENAI_ENDPOINT_NAME = "OPENAI_ENDPOINT";

  public String openaiKey;
  public String openaiEndpoint;

  @Value("${DAPR_GRPC_PORT}")
  int daprAgentPort;

  @PostConstruct
  void init() {
    var client = new DaprClientBuilder()
      .build();
    //Using Dapr SDK to get a secret
    Map<String, String> openaiKeyMap = client.getSecret(SECRET_STORE_NAME, OPENAI_KEY_NAME).block();
    Map<String, String> openaiEndpointMap = client.getSecret(SECRET_STORE_NAME, OPENAI_ENDPOINT_NAME).block();

    openaiKey = openaiKeyMap.get(OPENAI_KEY_NAME);
    openaiEndpoint = openaiEndpointMap.get(OPENAI_ENDPOINT_NAME);
  }
}
