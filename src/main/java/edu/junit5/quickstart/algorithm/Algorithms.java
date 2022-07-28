package edu.junit5.quickstart.algorithm;

public class Algorithms {

  private final Algorithm[] availableAlgorithm;

  public Algorithms() {
    this.availableAlgorithm = new Algorithm[]{
            new AES(),
            new PBEWithSHA256And128BitAESCBCBC(),
            new PBEWithSHAAnd40BitRC4(),
            new SHA256withDSA()
    };
  }

  public String getNameForParameterGeneration(String algorithmName) {
    for (Algorithm algorithm : availableAlgorithm) {
      if (algorithm.getBouncyCastleName().equals(algorithmName)) {
        return algorithm.getNameForParameterGeneration();
      }
    }
    return null;
  }
}
