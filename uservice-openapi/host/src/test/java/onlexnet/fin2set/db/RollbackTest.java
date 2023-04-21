package onlexnet.fin2set.db;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.Cleanup;
import lombok.SneakyThrows;

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ExtendWith(DbExtension.class)
@ActiveProfiles("test")
public class RollbackTest {


  @Autowired
  SpringLiquibase liquibaseBean;

  // We need rollback for database operations so that rollback procedure is
  // required for all liquibase changesets.

  @Test
  @SneakyThrows
  void should_rollback_all_changes() {
    var dataSource = liquibaseBean.getDataSource();
    var connection = dataSource.getConnection();
    var database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

    var resourceAccessor = new ClassLoaderResourceAccessor();
    @Cleanup
    var liquibase = new Liquibase("db/changeLog.yaml", resourceAccessor, database);
    var initialTag = "v0"; // well-known tag in database used for rollback tests
    Assertions
        .assertThatCode(() -> liquibase.rollback(initialTag, (String) null))
        .as("All operation should include rollback operation")
        .doesNotThrowAnyException();
  }

}
