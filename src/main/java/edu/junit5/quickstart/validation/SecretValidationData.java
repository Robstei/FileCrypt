package edu.junit5.quickstart.validation;

import java.security.Key;

public class SecretValidationData {

  private final Key key;

  public SecretValidationData(Key key) {
    this.key = key;
  }

  public Key getKey() {
    return key;
  }
}
