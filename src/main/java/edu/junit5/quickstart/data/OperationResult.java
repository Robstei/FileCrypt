package edu.junit5.quickstart.data;

/**
 * Cryptographic operation result to show appropriated message in UI
 *
 * @author Robin Steil
 */
public class OperationResult {
  private final boolean success;
  private final String message;
  private final Exception exception;


  /**
   * Instantiates a new Operation result. Intended use is for successful
   * operations, where no message is needed.
   */
  public OperationResult() {
    this(true, "", null);
  }

  /**
   * Instantiates a new Operation result.
   *
   * @param success whether the operation was successful or not
   * @param message the message to show the user
   */
  public OperationResult(boolean success, String message) {
    this(success, message, null);
  }

  /**
   * Instantiates a new Operation result.
   *
   * @param success   the success
   * @param message   the message
   * @param exception the exception
   */
  public OperationResult(boolean success, String message, Exception exception) {
    this.success = success;
    this.message = message;
    this.exception = exception;
  }


  /**
   * returns whether the operation was successful.
   *
   * @return whether the operation was successful
   */
  public boolean isSuccess() {
    return success;
  }

  /**
   * Gets the message to show the user.
   *
   * @return the message to show the user.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets exception.
   *
   * @return the exception
   */
  public Exception getException() {
    return exception;
  }
}
