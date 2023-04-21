package onlexnet.fin2set.nordigen.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
@EnableAutoConfiguration
class FeignConfigurer {
}
