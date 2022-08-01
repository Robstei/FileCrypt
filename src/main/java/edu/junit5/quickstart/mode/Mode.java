package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.Padding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Mode {

  final private ArrayList<Padding> possiblePaddings;
  private String bouncyCastleName;
  private String UIName;
  private boolean needsAlgorithmParameter;
  private boolean useThisNameForParameterGeneration;

  Mode(String bouncyCastleName,
       String UIName,
       ArrayList<Padding> possiblePaddings,
       boolean needsAlgorithmParameter,
       boolean useThisNameForParameterGeneration) {
    this.bouncyCastleName = bouncyCastleName;
    this.UIName = UIName;
    this.needsAlgorithmParameter = needsAlgorithmParameter;
    this.possiblePaddings = possiblePaddings;
    this.useThisNameForParameterGeneration =
            useThisNameForParameterGeneration;
  }

  public String getBouncyCastleName() {
    return bouncyCastleName;
  }

  public boolean isValidPadding(String key) {
    for (Padding padding : possiblePaddings) {
      if (padding.getBouncyCastleName().equals("key")) {
        return true;
      }
    }
    return false;
  }

  public boolean isNeedsAlgorithmParameter() {
    return needsAlgorithmParameter;
  }

  public boolean isUseThisNameForParametersGeneration() {
    return useThisNameForParameterGeneration;
  }

  public ArrayList<String> getValidPaddingNames() {
    return possiblePaddings.stream().map(
            padding -> padding.getBouncyCastleName()).collect(
            Collectors.toCollection(ArrayList::new));
  }
}