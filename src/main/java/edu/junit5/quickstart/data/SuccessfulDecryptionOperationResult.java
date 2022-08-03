package edu.junit5.quickstart.data;

/**
 * Successful decryption operation result.
 *
 * @author Robin Steil
 */
public class SuccessfulDecryptionOperationResult extends OperationResult {
  /**
   * Instantiates a new Successful decryption operation result.
   *
   * @param fileName the file name
   */
  public SuccessfulDecryptionOperationResult(String fileName) {
    super(true, "Saved decrypted File as " + fileName);
  }
}
