package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.Mode;

/**
 * Abstract representation of a cryptographic algorithm with its properties
 *
 * @author Robin Steil
 */
public abstract class Algorithm {

  private final String bouncyCastleName;
  private final String UIName;
  private final Mode[] possibleModes;
  private final String nameForParameterGeneration;
  private final boolean mightNeedAlgorithmParameters;

  /**
   * Instantiates a new Algorithm.
   *
   * @param bouncyCastleName             the bouncy castle name
   * @param UIName                       the ui name
   * @param possibleModes                the possible modes
   * @param mightNeedAlgorithmParameters the might need algorithm parameters
   * @param nameForParameterGeneration   the name for parameter generation
   */
  public Algorithm(String bouncyCastleName, String UIName,
                   Mode[] possibleModes, boolean mightNeedAlgorithmParameters,
                   String nameForParameterGeneration) {
    this.bouncyCastleName = bouncyCastleName;
    this.UIName = UIName;
    this.possibleModes = possibleModes;
    this.mightNeedAlgorithmParameters = mightNeedAlgorithmParameters;
    this.nameForParameterGeneration = nameForParameterGeneration;
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
   * Returns if the Algorithm might need parameter.
   *
   * @return if the Algorithm might need parameter.
   */
  public boolean doesMightNeedAlgorithmParameters() {
    return mightNeedAlgorithmParameters;
  }

  /**
   * Gets name for parameter generation.
   *
   * @return the name for parameter generation
   */
  public String getNameForParameterGeneration() {
    return nameForParameterGeneration;
  }
}



