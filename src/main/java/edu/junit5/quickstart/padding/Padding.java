package edu.junit5.quickstart.padding;

/**
 * Abstract representation of a cryptographic padding with its properties
 *
 * @author Robin Steil
 */
public abstract class Padding {

  private final String bouncyCastleName;
  private final String UIName;

  /**
   * Instantiates a new Mode.
   *
   * @param bouncyCastleName the bouncy castle name
   * @param UIName           the ui name
   */
  public Padding(String bouncyCastleName, String UIName) {
    this.bouncyCastleName = bouncyCastleName;
    this.UIName = UIName;
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
}
