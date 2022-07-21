package edu.junit5.quickstart.password;

public class PublicPasswordData {
  private final byte[] salt;


  public PublicPasswordData(byte[] salt) {
    this.salt = salt;
  }

  public byte[] getSalt() {
    return salt;
  }


}
