package edu.junit5.quickstart.data;

import edu.junit5.quickstart.algorithm.Algorithm;
import edu.junit5.quickstart.algorithm.Algorithms;
import edu.junit5.quickstart.mode.Mode;
import edu.junit5.quickstart.mode.Modes;

/**
 * Represents a symmetric encryption transformation used by the java JCAJCE
 *
 * @author Robin Steil
 */
public class Transformation {
  private final String algorithmName;
  private final String modeName;
  private final String paddingName;

  /**
   * Instantiates a new Transformation.
   *
   * @param algorithmName the algorithm name
   * @param modeName      the mode name
   * @param paddingName   the padding name
   */
  public Transformation(String algorithmName, String modeName,
                        String paddingName) {
    this.algorithmName = algorithmName;
    this.modeName = modeName;
    this.paddingName = paddingName;
  }

  /**
   * Gets algorithm name.
   *
   * @return the algorithm name
   */
  public String getAlgorithmName() {
    return algorithmName;
  }

  /**
   * Gets mode name.
   *
   * @return the mode name
   */
  public String getModeName() {
    return modeName;
  }

  /**
   * Gets padding name.
   *
   * @return the padding name
   */
  public String getPaddingName() {
    return paddingName;
  }


  /**
   * Transformations can consist of an algorithm or an algorithm and a mode
   * or an algorithm, a mode and padding. Does necessary checks and returns
   * a String representation for further usage with the JCAJCE.
   *
   * @return the String representation of the Transformation
   */
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

  /**
   * Gets name for parameter generation. Necessary because the name for
   * parameter generation might differ from the String representation or the
   * algorithm name.
   *
   * @return the name for parameter generation
   */
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
