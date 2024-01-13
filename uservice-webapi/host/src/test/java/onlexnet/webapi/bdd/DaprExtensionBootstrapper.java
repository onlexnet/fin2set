package onlexnet.webapi.bdd;

import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.TestContext;

import onlexnet.webapi.DaprRunner;

/**
 * For Cucumber we would like to run 3 things in the same time:1
 * Cucumber, SpringBoot, DAPR
 * The main technical issue is, Cucumber+Spring integration does not honor @ExtendWith, especially when we use
 * @ExtendWith(DaprExtension.class) to run database before tests.
 * As w can't find any way how to run them together, th bootstrapper runs Db and
 * Spring in one piece here.
 * the issue we trying solve: https://stackoverflow.com/questions/74431287/junit-5-how-to-use-extensions-with-a-test-suite
 */
public final class DaprExtensionBootstrapper extends SpringBootTestContextBootstrapper {

  @Override
  public TestContext buildTestContext() {
    var runner = new DaprRunner();
    var disposer = runner.start();
    var testContext = super.buildTestContext();
    var appContext = (AbstractApplicationContext) testContext.getApplicationContext();
    appContext.addApplicationListener(e -> {
      if (e instanceof ContextClosedEvent) {
        disposer.close();
      }
    });
    return testContext;
  }



}
