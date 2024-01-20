package onlexnet.webapi.openai;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Nonnull;

@ConfigurationProperties("fin2set.openai")
@Validated
record OpenAiProperties(
  @Nonnull String key, 
  @Nonnull String endpoint) {
}
