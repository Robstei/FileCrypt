package edu.junit5.quickstart.symmetricEncryption;

import edu.junit5.quickstart.data.Transformation;

/**
 * The type Public pre encryption data.
 *
 * @author Robin Steil
 */
public class PublicPreEncryptionData {

  private final byte[] bytesToEncrypt;
  private final String algorithm;
  private final String mode;
  private final String padding;

  private final int keySize;

  /**
   * Instantiates a new Public pre encryption data.
   *
   * @param bytesToEncrypt the bytes to encrypt
   * @param transformation the transformation
   * @param keySize        the key size
   */
  public PublicPreEncryptionData(byte[] bytesToEncrypt,
                                 Transformation transformation, int keySize) {
    this.bytesToEncrypt = bytesToEncrypt;
    this.algorithm = transformation.getAlgorithmName();
    this.mode = transformation.getModeName();
    this.padding = transformation.getPaddingName();
    this.keySize = keySize;
  }

  /**
   * Get bytes to encrypt.
   *
   * @return bytes to encrypt
   */
  public byte[] getBytesToEncrypt() {
    return bytesToEncrypt;
  }

  /**
   * Gets algorithm.
   *
   * @return the algorithm
   */
  public String getAlgorithm() {
    return algorithm;
  }

  /**
   * Gets mode.
   *
   * @return the mode
   */
  public String getMode() {
    return mode;
  }

  /**
   * Gets padding.
   *
   * @return the padding
   */
  public String getPadding() {
    return padding;
  }

  /**
   * Gets key size.
   *
   * @return the key size
   */
  public int getKeySize() {
    return keySize;
  }

  /**
   * Gets transformation.
   *
   * @return the transformation
   */
  public Transformation getTransformation() {
    return new Transformation(algorithm, mode, padding);
  }
}
