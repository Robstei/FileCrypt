package edu.junit5.quickstart.password;

public class PublicPasswordData {
  private final String algorithm;
  private final byte[] salt;

  private final int keyLength;


  public PublicPasswordData(String algorithm, byte[] salt, int keyLength) {
    this.algorithm = algorithm;
    this.salt = salt;
    this.keyLength = keyLength;
  }

  public byte[] getSalt() {
    return salt;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public int getKeyLength() {
    return keyLength;
  }
}
