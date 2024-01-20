package onlexnet.webapi.bdd;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.cucumber.spring.CucumberContextConfiguration;
import onlexnet.webapi.Application;

@CucumberContextConfiguration
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  classes = { Application.class, PortsConfigurer.class })
@ActiveProfiles("test")
public class CucumberContextConfigurer {
}
