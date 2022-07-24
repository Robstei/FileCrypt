package edu.junit5.quickstart.model;

import edu.junit5.quickstart.state.Transformation;

public class PublicPostEncryptionData {

  private final byte[] encryptedBytes;
  private final String algorithm;
  private final String mode;
  private final String padding;
  private final byte[] algorithmParametersAsBytes;


  PublicPostEncryptionData(byte[] encryptedBytes, Transformation transformation,
                           byte[] algorithmParametersAsBytes) {
    this.encryptedBytes = encryptedBytes;
    this.algorithm = transformation.getAlgorithm();
    this.mode = transformation.getMode();
    this.padding = transformation.getPadding();
    this.algorithmParametersAsBytes = algorithmParametersAsBytes;
  }

  public byte[] getEncryptedBytes() {
    return encryptedBytes;
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

  public Transformation getTransformation() {
    return new Transformation(algorithm, mode, padding);
  }

  public byte[] getAlgorithmParametersAsBytes() {
    return algorithmParametersAsBytes;
  }
}
