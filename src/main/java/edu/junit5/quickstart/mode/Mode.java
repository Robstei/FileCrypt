package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.Padding;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Abstract representation of a cryptographic mode with its properties
 *
 * @author Robin Steil
 */
public abstract class Mode {

  final private ArrayList<Padding> possiblePaddings;

  private final String bouncyCastleName;
  private final String uiName;

  private final boolean needsAlgorithmParameter;
  private final boolean useThisNameForParameterGeneration;

  /**
   * Instantiates a new Mode.
   *
   * @param bouncyCastleName                  the bouncy castle name
   * @param uiName                            the ui name
   * @param possiblePaddings                  the possible paddings
   * @param needsAlgorithmParameter           the needs algorithm parameter
   * @param useThisNameForParameterGeneration the use this name for parameter
   *                                          generation
   */
  Mode(String bouncyCastleName,
       String uiName,
       ArrayList<Padding> possiblePaddings,
       boolean needsAlgorithmParameter,
       boolean useThisNameForParameterGeneration) {
    this.bouncyCastleName = bouncyCastleName;
    this.uiName = uiName;
    this.needsAlgorithmParameter = needsAlgorithmParameter;
    this.possiblePaddings = possiblePaddings;
    this.useThisNameForParameterGeneration =
            useThisNameForParameterGeneration;
  }

  /**
   * Gets bouncy castle name.
   *
   * @return the bouncy castle name
   */
  public String getBouncyCastleName() {
    return bouncyCastleName;
  }

  /**
   * Checks if the mode supports a padding with the name paddingName.
   *
   * @param paddingName the name of the padding
   * @return true if the mode supports a padding with the name, false otherwise
   */
  public boolean isValidPadding(String paddingName) {
    for (Padding padding : possiblePaddings) {
      if (padding.getBouncyCastleName().equals(paddingName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether the mode needs algorithm parameters
   *
   * @return true if mode  needs algorithm parameters
   */
  public boolean isNeedsAlgorithmParameter() {
    return needsAlgorithmParameter;
  }

  /**
   * Checks whether the mode's algorithm parameters need to be generated with
   * the modes name
   *
   * @return true if the name of the mode needs to be used for the generation
   * of the parameter, false otherwise
   */
  public boolean useThisNameForParametersGeneration() {
    return useThisNameForParameterGeneration;
  }

  /**
   * Gets the valid padding names for the mode
   *
   * @return the valid padding names as ArrayList
   */
  public ArrayList<String> getValidPaddingNames() {
    return possiblePaddings.stream().map(
            Padding::getBouncyCastleName).collect(
            Collectors.toCollection(ArrayList::new));
  }

  /**
   * Gets ui name.
   *
   * @return the uiName
   */
  public String getUiName() {
    return uiName;
  }
}