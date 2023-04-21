package onlexnet.fin2set;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.spring.CucumberContextConfiguration;
import onlexnet.fin2set.db.SpringBootDbTestContextBootstrapper;

@CucumberContextConfiguration
@ContextConfiguration(classes = {Program.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@BootstrapWith(SpringBootDbTestContextBootstrapper.class)
@ActiveProfiles("test")
public class CucumberSpringContextConfig {
}
