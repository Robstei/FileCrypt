package edu.junit5.quickstart.model;

import java.security.Key;

public class SecretEncryptionData {
  private final Key key;


  public SecretEncryptionData(Key key) {
    this.key = key;
  }

  public Key getKey() {
    return key;
  }

}
