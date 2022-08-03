package edu.junit5.quickstart.data;

/**
 * Failed validation operation result.
 *
 * @author Robin Steil
 */
public class FailedValidationOperationResult extends OperationResult {
  /**
   * Instantiates a new Failed validation operation result.
   *
   * @param validationName the validation name
   */
  public FailedValidationOperationResult(String validationName) {
    super(false, "Validation with " + validationName +
            " was not successful. Did not decrypt file");
  }
}
