package onlexnet.fin2set;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EnableFeignClients
public class Program {

	public static void main(String[] args) {
		SpringApplication.run(Program.class, args);
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("springshop-public")
				.pathsToMatch("/api/**")
				.build();
	}
	
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Fin2Set API")
						.description("Sandbox accountID: <p> " +
								"7e944232-bda9-40bc-b784-660c7ab5fe78 <p> " +
								"99a0bfe2-0bef-46df-bff2-e9ae0c6c5838 <p> " +
								"Sandbox bankID: SANDBOXFINANCE_SFIN0000 <p> " +
								"Example created requisition id: a69b3c58-4412-4865-954b-70a4d20a08d6")
						.version("version 1.0.0")
						.license(new License().name("License").url("...")))
				.externalDocs(new ExternalDocumentation()
						.description("descr.")
						.url("url"));
	}

}
