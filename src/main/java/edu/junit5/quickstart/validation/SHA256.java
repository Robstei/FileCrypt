package edu.junit5.quickstart.validation;

public class SHA256 extends Validation {

  public SHA256() {
    super("SHA-256", "SHA-256", ValidationType.DIGGEST, null);
  }
}
