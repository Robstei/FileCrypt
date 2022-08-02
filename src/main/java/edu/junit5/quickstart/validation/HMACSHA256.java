package edu.junit5.quickstart.validation;

/**
 * Representation of HMACSHA256 validation
 *
 * @author Robin Steil
 */
public class HMACSHA256 extends Validation {
  
  /**
   * Instantiates HMACSHA256 with its properties for later lockups
   */
  public HMACSHA256() {
    super("HMACSHA256",
          "HMACSHA256",
          ValidationType.MAC,
          "HMACSHA256");
  }
}
