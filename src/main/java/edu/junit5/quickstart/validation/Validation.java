package edu.junit5.quickstart.validation;

/**
 * Abstract representation of a cryptographic validation (digest or MAC) with
 * its properties
 *
 * @author Robin Steil
 */
public abstract class Validation {

  private final String bouncyCastleName;
  private final String UIName;
  private final ValidationType validationType;
  private final String keyInitializer;

  /**
   * Instantiates a new Validation.
   *
   * @param bouncyCastleName the bouncy castle name
   * @param UIName           the ui name
   * @param validationType   the validation type
   * @param keyInitializer   the key initializer
   */
  public Validation(String bouncyCastleName, String UIName,
                    ValidationType validationType, String keyInitializer) {
    this.bouncyCastleName = bouncyCastleName;
    this.UIName = UIName;
    this.validationType = validationType;
    this.keyInitializer = keyInitializer;
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
   * Gets ui name.
   *
   * @return the ui name
   */
  public String getUIName() {
    return UIName;
  }

  /**
   * Gets validation type.
   *
   * @return the validation type
   */
  public ValidationType getValidationType() {
    return validationType;
  }

  /**
   * Gets key initializer.
   *
   * @return the key initializer
   */
  public String getKeyInitializer() {
    return keyInitializer;
  }
}
