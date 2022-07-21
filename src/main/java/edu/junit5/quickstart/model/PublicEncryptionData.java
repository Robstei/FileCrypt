package edu.junit5.quickstart.model;

import edu.junit5.quickstart.state.Transformation;

import java.security.AlgorithmParameters;

public class PublicEncryptionData {

  private final byte[] encryptedBytes;
  private final String algorithm;
  private final String mode;
  private final String padding;
  private final AlgorithmParameters algorithmParameters;

  private final String algorithmForParameters;


  PublicEncryptionData(byte[] encryptedBytes, Transformation transformation,
                       AlgorithmParameters algorithmParameters,
                       String algorithmForParameters) {
    this.encryptedBytes = encryptedBytes;
    this.algorithm = transformation.getAlgorithm();
    this.mode = transformation.getMode();
    this.padding = transformation.getPadding();
    this.algorithmParameters = algorithmParameters;
    this.algorithmForParameters = algorithmForParameters;
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

  public AlgorithmParameters getAlgorithmParameters() {
    return algorithmParameters;
  }

  public String getAlgorithmForParameters() {
    return algorithmForParameters;
  }
}
