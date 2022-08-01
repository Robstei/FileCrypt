package edu.junit5.quickstart.data;

import edu.junit5.quickstart.algorithm.Algorithm;
import edu.junit5.quickstart.algorithm.Algorithms;
import edu.junit5.quickstart.mode.Mode;
import edu.junit5.quickstart.mode.Modes;

public class Transformation {
  private final String algorithmName;
  private final String modeName;
  private final String paddingName;

  public Transformation(String algorithmName, String modeName,
                        String paddingName) {
    this.algorithmName = algorithmName;
    this.modeName = modeName;
    this.paddingName = paddingName;
  }

  public String getAlgorithmName() {
    return algorithmName;
  }

  public String getModeName() {
    return modeName;
  }

  public String getPaddingName() {
    return paddingName;
  }

  @Override
  public String toString() {
    String string = algorithmName;
    if (modeName != null && !modeName.equals("")) {
      string += "/" + modeName;
    }
    if ((paddingName != null) && !paddingName.equals("")) {
      string += "/" + paddingName;
    }
    return string;
  }

  public String getNameForParameterGeneration() {
    Algorithms algorithms = new Algorithms();
    Algorithm algorithm = algorithms.getAlgorithm(algorithmName);

    Modes modes = new Modes();
    Mode mode = modes.getModeByName(modeName);

    if (!algorithm.doesMightNeedAlgorithmParameters()) {
      return null;
    }

    if (mode != null) {
      if (!mode.isNeedsAlgorithmParameter()) {
        return null;
      }

      if (mode.isUseThisNameForParametersGeneration()) {
        return mode.getBouncyCastleName();
      }
    }
    return algorithm.getNameForParameterGeneration();
  }
}
