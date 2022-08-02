package edu.junit5.quickstart.validation;

/**
 * Representation of AESCMAC validation
 *
 * @author Robin Steil
 */
public class AESCMAC extends Validation {

  /**
   * Instantiates AESCMAC with its properties for later lockups
   */
  public AESCMAC() {
    super("AESCMAC",
          "AESCMAC",
          ValidationType.MAC,
          "AES");
  }
}
