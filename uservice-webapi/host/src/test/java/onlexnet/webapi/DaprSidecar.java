package onlexnet.webapi;

import java.nio.file.Path;
import java.util.List;

import org.testcontainers.utility.MountableFile;

import io.diagrid.dapr.DaprContainer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Designed to be used to run single instance for all tests in single module.
 */
@Slf4j
public final class DaprSidecar {

  public static DaprContainer daprContainer;

  /**
   * tarts database and sets all exopected environments variables, used to
   * construct propoer jdbc connection.
   *
   * @return AutoCloseable that will stop the server and restore all environment
   *         variables to their original values.
   */
  @SneakyThrows
  public SafeAutoCloseable start(Path componentPath) {
    var file = componentPath.resolve("local-secret-store.yaml");
    var b = file.toFile().exists();
    log.info(Boolean.toString(b));
    daprContainer = new DaprContainer("daprio/daprd")
        .withAppName("test-app")
        // .withAccessToHost(true)
        // .withClasspathResourceMapping(componentParh.toAbsolutePath().toString(), , BindMode.READ_ONLY, SelinuxContext.SHARED)
        // .withCopyToContainer(null, null)
        // .withCopyFileToContainer(MountableFile.forHostPath(componentPath.resolve("local-secret-store.yaml") ), "/components")
        .withCopyFileToContainer(MountableFile.forHostPath(componentPath.resolve("local-secret-store.yaml")), "/components/local-secret-store.yaml")
        .withCopyFileToContainer(MountableFile.forHostPath(componentPath.getParent().resolve("dapr-local-secrets.json")), "/dapr-local-secrets.json")
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

  /**
   * AutoCloseable which doesn't be to close with handling exception from close
   * method.
   */
  @FunctionalInterface
  public interface SafeAutoCloseable extends AutoCloseable {
    void close();
  }

}
