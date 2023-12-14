package onlexnet.webapi.bdd;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;

import io.cucumber.spring.CucumberContextConfiguration;
import onlexnet.webapi.Application;

@CucumberContextConfiguration
// @ActiveProfiles({Profiles.App.TEST})
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  properties = { "DAPR_GRPC_PORT=0" }, classes = { Application.class, PortsConfigurer.class })
@BootstrapWith(DaprExtensionBootstrapper.class)
public class CucumberContextConfigurer {
}