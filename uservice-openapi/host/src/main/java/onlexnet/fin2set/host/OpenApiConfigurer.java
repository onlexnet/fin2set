package onlexnet.fin2set.host;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
class OpenApiConfigurer {

    @Bean
    GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("springshop-public")
				.pathsToMatch("/api/**")
				.build();
	}

    @Bean
    OpenAPI springShopOpenAPI(BuildProperties buildProperties) {
		return new OpenAPI()
				.info(new Info().title("Fin2Set API")
						.description("Sandbox accountID: <p> " +
								"49913b6f-20c5-45a4-8062-8a91d342a376 <p> " +
								"260ff4a4-958b-4501-b914-1fe70216f73b <p> " +
								"Sandbox bankID: SANDBOXFINANCE_SFIN0000 <p> " +
								"Example created requisition id: a69b3c58-4412-4865-954b-70a4d20a08d6")
						.version(buildProperties.getVersion())
						.license(new License().name("License").url("...")))
				.externalDocs(new ExternalDocumentation()
						.description("descr.")
						.url("url"));
	}
}
