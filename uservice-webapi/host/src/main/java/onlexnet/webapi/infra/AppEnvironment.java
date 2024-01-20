package onlexnet.webapi.infra;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import io.dapr.client.DaprClientBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Cleanup;
import lombok.SneakyThrows;

/**
 * For proper working the application needs some extra environment variables.
 * Because it need to be defined before creation of beans, it is done using {@link EnvironmentPostProcessor}.
 * We need to add two main sources:
 * - DAPR secrets. Of course app may read DAPR secrets using imperative code, but we would like to have them injected as it is best practice.
 *   This part may be removed in the future if/when Spring have extension to get such secrets from DAP by some kind of binding. 
 * - .env file, reused only for local development and tests. The file is not used / does not exist on prod
 */
@Order(Ordered.LOWEST_PRECEDENCE)
class AppEnvironment implements EnvironmentPostProcessor {

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication app) {

    loadDaprSecrets(env);

    var activeProsiles = Set.of(env.getActiveProfiles());

    if (activeProsiles.contains("test")) {
      loadEnvValues(env, "../");
    }

    if (activeProsiles.contains("local")) {
      loadEnvValues(env, "./");
    }
  }

  // secret store named hardcoded in DAPR components
  private static final String SECRET_STORE_NAME = "azurekeyvault";

  @SneakyThrows
  private void loadDaprSecrets(ConfigurableEnvironment env) {
    var maxTimeWaitingForDaprInMilis = 3_000;
    @Cleanup
    var client = new DaprClientBuilder()
        .build();
    client.waitForSidecar(maxTimeWaitingForDaprInMilis).block();
      
    // load all secrets from azure keyvault and inject them as environment variables
    var secretsAsStrangeMap = client.getBulkSecret(SECRET_STORE_NAME, Map.of()).block();
    var secrets = secretsAsStrangeMap.entrySet().stream()
        .map(it -> it.getValue())
        .flatMap(it -> it.entrySet().stream())
        .collect(Collectors.toMap(it -> it.getKey(), it -> (Object) it.getValue(), (v1, v2) -> v1));
    var propertySource = new MapPropertySource("dapr-" + SECRET_STORE_NAME, secrets);
    var propertySources = env.getPropertySources();
    propertySources.addLast(propertySource);
  }

  /**
   * @param envFilePath folder where we may find .env file with optional environment variables
   */
  void loadEnvValues(ConfigurableEnvironment env, String envPath) {
    var dotenv = Dotenv.configure()
      .directory(envPath)
      .load();

    var envMap = new HashMap<String, Object>();
    dotenv.entries().forEach(it -> {
      envMap.put(it.getKey(), it.getValue());
    });

    var propertySource = new MapPropertySource(".env", envMap);
    var propertySources = env.getPropertySources();
    propertySources.addLast(propertySource);
  }
}
