package edu.junit5.quickstart.validation;

/**
 * Enum to indicate if a validation is a digest or a mac.
 *
 * @author Robin Steil
 */
public enum ValidationType {
  /**
   * Digest validation type.
   */
  DIGEST,
  /**
   * Mac validation type.
   */
  MAC
}
