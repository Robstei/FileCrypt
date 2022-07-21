package edu.junit5.quickstart.algorithm;

public class Algorithms {

  private final Algorithm[] availableAlgorithm;

  public Algorithms() {
    this.availableAlgorithm = new Algorithm[]{
            new AES(),
            new PBEWithSHA256And128BitAESCBCBC(),
            new PBEWithSHAAnd40BitRC4(),
    };
  }

  public String getNameForParameterGeneration(String name) {
    for (Algorithm algorithm : availableAlgorithm) {
      if (algorithm.getBouncyCastleName().equals(name)) {
        return algorithm.getNameForParameterGeneration();
      }
    }
    return null;
  }
}
