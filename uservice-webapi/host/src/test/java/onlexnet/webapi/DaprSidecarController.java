package onlexnet.webapi;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import io.diagrid.dapr.DaprContainer;
import lombok.SneakyThrows;
import onlexnet.webapi.infra.StringProperty;

/**
 * Designed to be used to run single instance for all tests in single module.
 */
public final class DaprSidecarController {

  public static DaprContainer daprContainer;

  /**
   * Starts DAPR sidecar and sets all exopected environments variables
   *
   * @return AutoCloseable that will stop the server and restore all environment
   *         variables to their original values.
   * @param projectPath pthh where build pom.xml is located
   */
  @SneakyThrows
  public SafeAutoCloseable start(Path projectPath, StringProperty clientId, StringProperty clientSecret) {
    var componentsDir = projectPath.resolve("components");

    daprContainer = new DaprContainer("daprio/daprd")
        .withAppName("test-app")
        .withEnv(Map.of(clientId.getEnvName(), clientId.get(), clientSecret.getEnvName(), clientSecret.get()))
        .withFileSystemBind(componentsDir.toAbsolutePath().toString(), "/components" )
        .withAppPort(8081);
    
    daprContainer.start();

    var items = List.of(
        // DAPR properties: https://docs.dapr.io/reference/environment/
        setProperty("dapr.grpc.port", daprContainer.getGRPCPort()));
    return () -> items.forEach(SafeAutoCloseable::close);
  }

  private SafeAutoCloseable setProperty(String propertyName, int propertyValue) {
    return setProperty(propertyName, Integer.toString(propertyValue));
  }

  private SafeAutoCloseable setProperty(String propertyName, String propertyValue) {
    var actual = System.getProperty(propertyName);
    System.setProperty(propertyName, propertyValue);
    return actual == null
        ? () -> System.clearProperty(propertyName)
        : () -> System.setProperty(propertyName, actual);
  }

}
