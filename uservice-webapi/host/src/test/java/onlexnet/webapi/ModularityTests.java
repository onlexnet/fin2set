package onlexnet.webapi;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

// @SpringBootTest
// @ExtendWith(DaprExtension.class)
class ModularityTests {

  ApplicationModules modules = ApplicationModules.of(Application.class);

	@Test
	void verifyModularity() {

    // Writing the application module arranagement to the console
    modules.forEach(System.out::println);
    
    // Trigger verification
    modules.verify();
	}

}
