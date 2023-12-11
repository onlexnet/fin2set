package onlexnet.webapi;

import java.nio.file.Paths;

import onlexnet.webapi.infra.StringProperty;

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

    var clientId = new StringProperty("FIN2SET_CLIENT_ID");
    var clientSecret = new StringProperty("FIN2SET_CLIENT_SECRET");

    disposer = dbRunner.start(projectRootPath, clientId, clientSecret);
    return disposer;
  }

}
