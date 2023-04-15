package onlexnet.fin2set.db;


import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Reusable extension to start local DB instance for µservices based on
 * its databases.
 */
public final class DbExtension implements BeforeAllCallback,
    AfterAllCallback {

  private AutoCloseable disposer = () -> { };

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    var dbRunner = new DbRunner();
    disposer = dbRunner.start();
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    disposer.close();
  }

}
