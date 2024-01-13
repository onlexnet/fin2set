package onlexnet.webapi;

import java.nio.file.Paths;

import onlexnet.webapi.infra.StringProperty;

/**
 * Reusable extension to start local DAPR instance for µservices based on
 * its databases.
 * Context:
 * on real environment, DAPR is started and configured using terraform scripots and all environment variables
 * (especially related to service principal id/secret) are already on place.
 * In tests we have missing two important variables which are enough to read the reast of secrets from key vault.
 * They are: FIN2SET_CLIENT_ID and FIN2SET_CLIENT_SECRET. They are used to instaniate principal able to read the rest
 * of secrets.
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
