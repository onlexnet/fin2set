package onlexnet.fin2set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class Program {

	public static void main(String[] args) {
		SpringApplication.run(Program.class, args);
	}

	@Bean
	public Docket get() {
    // we have to be compatible with Swagge2 as Logic Apps are not compatible with OpenAPI 3
    // more: https://learn.microsoft.com/en-us/connectors/custom-connectors/define-openapi-definition#import-the-openapi-definition
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/**")) // Provides endpoints that meet the condition
				.apis((RequestHandlerSelectors.basePackage("onlexnet.fin2set"))) // Looking and share endpoints in folder "dj"
				.build()
				.apiInfo(createApiInfo());
	}

	// Sets up api information, leaves a business card
	private ApiInfo createApiInfo() {
		return new ApiInfo("Fin2Set",
				"Description",
				"1.00",
				"localhost:8080",
				new Contact("Damian Jarzębowski", "https://www.linkedin.com/in/damian-jarz%C4%99bowski-05aa32220/", "damian.r.jarzebowski@gmail.com"),
				"My own licence",
				"https://www.linkedin.com/in/damian-jarz%C4%99bowski-05aa32220/",
				Collections.emptyList()
		);
	}

}
