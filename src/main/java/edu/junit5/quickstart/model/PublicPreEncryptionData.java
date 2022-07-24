package edu.junit5.quickstart.model;

import edu.junit5.quickstart.state.Transformation;

public class PublicPreEncryptionData {

  private final byte[] bytesToEncrypt;
  private final String algorithm;
  private final String mode;
  private final String padding;

  private final int keySize;

  public PublicPreEncryptionData(byte[] bytesToEncrypt,
                                 Transformation transformation, int keySize) {
    this.bytesToEncrypt = bytesToEncrypt;
    this.algorithm = transformation.getAlgorithm();
    this.mode = transformation.getMode();
    this.padding = transformation.getPadding();
    this.keySize = keySize;
  }

  public byte[] getBytesToEncrypt() {
    return bytesToEncrypt;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public String getMode() {
    return mode;
  }

  public String getPadding() {
    return padding;
  }

  public int getKeySize() {
    return keySize;
  }

  public Transformation getTransformation() {
    return new Transformation(algorithm, mode, padding);
  }
}
