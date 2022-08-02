package edu.junit5.quickstart.algorithm;

/**
 * Registry of all algorithms implemented in FileCrypt.
 *
 * @author Robin Steil
 */
public class Algorithms {

  private final Algorithm[] availableAlgorithm;

  /**
   * Instance with reference to every implemented encryption algorithm
   */
  public Algorithms() {
    this.availableAlgorithm = new Algorithm[]{
            new AES(),
            new PBEWithSHA256And128BitAESCBCBC(),
            new PBEWithSHAAnd40BitRC4(),
            new SHA256withDSA()
    };
  }

  /**
   * Gets algorithm.
   *
   * @param algorithmName the algorithm name
   * @return the algorithm
   */
  public Algorithm getAlgorithm(String algorithmName) {
    for (Algorithm algorithm : availableAlgorithm) {
      if (algorithm.getBouncyCastleName().equals(algorithmName)) {
        return algorithm;
      }
    }
    return null;
  }

  /**
   * Gets name for parameter generation.
   *
   * @param algorithmName the algorithm name
   * @return the name for parameter generation or null if no match was found
   */
  public String getNameForParameterGeneration(String algorithmName) {
    for (Algorithm algorithm : availableAlgorithm) {
      if (algorithm.getBouncyCastleName().equals(algorithmName)) {
        return algorithm.getNameForParameterGeneration();
      }
    }
    return null;
  }
}
