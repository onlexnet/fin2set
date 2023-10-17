package onlexnet.webapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class ModularityTests {

  ApplicationModules modules = ApplicationModules.of(Application.class);

	@Test
	void verifyModularity() {

    // -> Module model
    System.out.print(modules.toString());
    
    // Trigger verification
    modules.verify();
	}

}
