package edu.junit5.quickstart.validation;

/**
 * Representation of SHA256 validation
 *
 * @author Robin Steil
 */
public class SHA256 extends Validation {

  /**
   * Instantiates SHA256 with its properties for later lockups
   */
  public SHA256() {
    super("SHA-256",
          "SHA-256",
          ValidationType.DIGEST,
          null);
  }
}
