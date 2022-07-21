package edu.junit5.quickstart.validation;

public abstract class Validation {

  private String bouncyCastleName;
  private String UIName;
  private ValidationType validationType;
  private String keyInitializer;

  public Validation(String bouncyCastleName, String UIName,
                    ValidationType validationType, String keyInitializer) {
    this.bouncyCastleName = bouncyCastleName;
    this.UIName = UIName;
    this.validationType = validationType;
    this.keyInitializer = keyInitializer;
  }

  public String getBouncyCastleName() {
    return bouncyCastleName;
  }

  public String getUIName() {
    return UIName;
  }

  public ValidationType getValidationType() {
    return validationType;
  }

  public String getKeyInitializer() {
    return keyInitializer;
  }
}
