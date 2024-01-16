package onlexnet.webapi.infra;

import java.util.HashMap;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import io.github.cdimascio.dotenv.Dotenv;

@Order(Ordered.LOWEST_PRECEDENCE)
class LocalEnvironment implements EnvironmentPostProcessor {

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication app) {

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

}
