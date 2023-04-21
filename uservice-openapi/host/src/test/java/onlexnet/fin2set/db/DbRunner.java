package onlexnet.fin2set.db;

import java.util.List;
import java.util.regex.Pattern;

import org.testcontainers.containers.MSSQLServerContainer;

import lombok.extern.slf4j.Slf4j;

/**
 * Designed to be used to run single instance for all tests in single module.
 */
@Slf4j
public final class DbRunner {

  // as we use Azure SQL v12, it is compatible with
  // SQL server v ??
  // more:
  // https://www.sqlservercentral.com/blogs/azure-sql-database-compatibility-with-sql-server
  static MSSQLServerContainer<?> database = new MSSQLServerContainer<>(
      "mcr.microsoft.com/mssql/server:2019-CU14-ubuntu-20.04")
      .acceptLicense();

  /**
   * tarts database and sets all exopected environments variables, used to
   * construct propoer jdbc connection.
   *
   * @return AutoCloseable that will stop the server and restore all environment
   *         variables to their original values.
   */
  public SafeAutoCloseable start() {
    database.start();
    var jdbc = database.getJdbcUrl();
    // sql server testcontainers does not return port as property, se lets extract using regex 
    var regex = "localhost:([^;]*)";
    var pattern = Pattern.compile(regex);
    var matcher = pattern.matcher(jdbc);
    matcher.find();
    var port = matcher.group(1);
    var items = List.of(
        setProperty("DATABASE_HOST", "localhost"),
        setProperty("DATABASE_PORT", port),
        setProperty("DATABASE_USERNAME", database.getUsername()),
        setProperty("DATABASE_PASSWORD", database.getPassword()));
    return () -> items.forEach(SafeAutoCloseable::close);
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
