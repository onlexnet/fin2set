package onlexnet.webapi.openai;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import onlexnet.webapi.DaprExtension;

@SpringBootTest
@ExtendWith(DaprExtension.class)
public class OpenAiTest {

  @Autowired
  OpenAi openAi;

  @Test
  void openAiCanAnswer() {

    var initial = new Message("Respond only: 'Hello'", MessageRole.USER);

    var response = openAi.getContinuation(List.of(initial));

    Assertions.assertThat(response).isEqualTo("Hello");
  }

  @Test
  void shouldUseApi() {
    var query = "What should I wear in Boston depending on the weather? Please advice with no more that 10 words.";
    var initial = new Message(query, MessageRole.USER);

    var response = openAi.getContinuation(List.of(initial));

    var a = openAi.getEmbedings(response);
    var b = openAi.getEmbedings("Light clothing, sunglasses, and sunscreen.");

    var similarity = cosineSimilarity(a, b);
    Assertions.assertThat(similarity).isGreaterThan(0.9);
  }


  // https://stackoverflow.com/questions/520241/how-do-i-calculate-the-cosine-similarity-of-two-vectors
  public static double cosineSimilarity(List<Double> vectorA, List<Double> vectorB) {
    return cosineSimilarity(vectorA.stream().mapToDouble(it -> it).toArray(), vectorB.stream().mapToDouble(it -> it).toArray());
  }

  public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
    double dotProduct = 0.0;
    double normA = 0.0;
    double normB = 0.0;
    for (int i = 0; i < vectorA.length; i++) {
        dotProduct += vectorA[i] * vectorB[i];
        normA += Math.pow(vectorA[i], 2);
        normB += Math.pow(vectorB[i], 2);
    }   
    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
}


}
