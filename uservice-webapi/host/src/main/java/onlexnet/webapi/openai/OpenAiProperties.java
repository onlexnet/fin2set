package onlexnet.webapi.openai;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("fin2set.openai")
record OpenAiProperties(String key, String endpoint) {
}
