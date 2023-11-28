package onlexnet.webapi;

import java.nio.file.Paths;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Reusable extension to start local DB instance for µservices based on
 * its databases.
 */
public final class DaprExtension implements BeforeAllCallback,
    AfterAllCallback {

  private AutoCloseable disposer = () -> { };

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    var projectRootPath = Paths.get("..").toAbsolutePath().normalize();
    var dbRunner = new DaprSidecarController();

    var clientId = System.getenv("FIN2SET_CLIENT_ID");
    var clientSecret = System.getenv("FIN2SET_CLIENT_SECRET");


    disposer = dbRunner.start(projectRootPath, clientId, clientSecret);
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    disposer.close();
  }

}
