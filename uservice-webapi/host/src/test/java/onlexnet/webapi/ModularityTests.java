package onlexnet.webapi;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.test.context.ActiveProfiles;

class ModularityTests {

  ApplicationModules modules = ApplicationModules.of(Application.class);

	@Test
	void verifyModularity() {

    // Writing the application module arranagement to the console
    modules.forEach(System.out::println);
    
    // Trigger verification
    modules.verify();
	}

  // @Test
  void writeDocumentationSnippets() {

    new Documenter(modules)
      .writeModulesAsPlantUml()
      .writeIndividualModulesAsPlantUml();
  }
}
