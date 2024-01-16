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

@Order(Ordered.LOWEST_PRECEDENCE)
class LocalEnvironment implements EnvironmentPostProcessor {

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication app) {

    loadDaprSecrets(env);

    String envPath;
    var profile = Set.of(env.getActiveProfiles());
    if (profile.contains("test")) {
      envPath = "../";
    } else if (profile.contains("local")) {
      envPath = "./";
    } else {
      return;
    }

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

  private static final String SECRET_STORE_NAME = "azurekeyvault";

  private void loadDaprSecrets(ConfigurableEnvironment env) {
    var maxTimeWaitingForDaprInMilis = 3_000;
    var client = new DaprClientBuilder()
        .build();
    client.waitForSidecar(maxTimeWaitingForDaprInMilis).block();
      
    var secretsAsStrangeMap = client.getBulkSecret(SECRET_STORE_NAME, Map.of()).block();
    var secrets = secretsAsStrangeMap.entrySet().stream()
        .map(it -> it.getValue())
        .flatMap(it -> it.entrySet().stream())
        .collect(Collectors.toMap(it -> it.getKey(), it -> (Object) it.getValue(), (v1, v2) -> v1));
    var propertySource = new MapPropertySource("dapr-" + SECRET_STORE_NAME, secrets);
    var propertySources = env.getPropertySources();
    propertySources.addLast(propertySource);
  }
}
