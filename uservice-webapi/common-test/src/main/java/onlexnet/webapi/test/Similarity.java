package onlexnet.webapi.test;

import java.util.List;

/**
 * Used by tests to compare embeddings.
 * more:
 * https://stackoverflow.com/questions/520241/how-do-i-calculate-the-cosine-similarity-of-two-vectors
 */
public class Similarity {

  /**
   * Similarity of provided embedings.
   *
   * @return 0 - 1, where 0 are different, 1 are identical
   */
  public static double cosine(List<Double> vectorA, List<Double> vectorB) {
    return cosineSimilarity(vectorA.stream().mapToDouble(it -> it).toArray(),
        vectorB.stream().mapToDouble(it -> it).toArray());
  }

  /**
   * Similarity of provided embedings.
   *
   * @return 0 - 1, where 0 are different, 1 are identical
   */
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
