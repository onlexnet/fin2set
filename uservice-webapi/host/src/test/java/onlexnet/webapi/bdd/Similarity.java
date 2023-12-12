package onlexnet.webapi.bdd;

import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Similarity {

  // https://stackoverflow.com/questions/520241/how-do-i-calculate-the-cosine-similarity-of-two-vectors
  public static double cosine(List<Double> vectorA, List<Double> vectorB) {
    return cosineSimilarity(vectorA.stream().mapToDouble(it -> it).toArray(),
        vectorB.stream().mapToDouble(it -> it).toArray());
  }

  public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
    var dotProduct = 0.0;
    var normA = 0.0;
    var normB = 0.0;
    for (int i = 0; i < vectorA.length; i++) {
      dotProduct += vectorA[i] * vectorB[i];
      normA += Math.pow(vectorA[i], 2);
      normB += Math.pow(vectorB[i], 2);
    }
    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
  }

}
