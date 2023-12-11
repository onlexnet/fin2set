package onlexnet.webapi;

import java.nio.file.Paths;

/**
 * Reusable extension to start local DB instance for µservices based on
 * its databases.
 */
public final class DaprRunner {

  private SafeAutoCloseable disposer = () -> {
  };

  public SafeAutoCloseable start() {
    var projectRootPath = Paths.get("..").toAbsolutePath().normalize();
    var dbRunner = new DaprSidecarController();

    var clientId = System.getenv("FIN2SET_CLIENT_ID");
    var clientSecret = System.getenv("FIN2SET_CLIENT_SECRET");

    disposer = dbRunner.start(projectRootPath, clientId, clientSecret);
    return disposer;
  }

}
