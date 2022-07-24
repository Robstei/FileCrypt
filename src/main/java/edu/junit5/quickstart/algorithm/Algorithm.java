package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.Mode;

public class Algorithm {

  private String bouncyCastleName;
  private String UIName;
  private Mode[] possibleModes;

  //TODO only show possible key sizes of the algorithm in the frontend. Might
  // be needed for passwords too
  private int[] possibleKeySizes;

  private String nameForParameterGeneration;

  public Algorithm(String bouncyCastleName, String UIName,
                   Mode[] possibleModes, String nameForParameterGeneration) {
    this.bouncyCastleName = bouncyCastleName;
    this.UIName = UIName;
    this.possibleModes = possibleModes;
    this.nameForParameterGeneration = nameForParameterGeneration;
  }

  public boolean valid(int keySize) {
    return true;
  }

  public String getBouncyCastleName() {
    return bouncyCastleName;
  }

  public String getUIName() {
    return UIName;
  }

  public Mode[] getPossibleModes() {
    return possibleModes;
  }

  public String getNameForParameterGeneration() {
    return nameForParameterGeneration;
  }
}



