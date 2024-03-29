package edu.junit5.quickstart.symmetricEncryption;

import edu.junit5.quickstart.data.CryptoData;
import edu.junit5.quickstart.data.Transformation;
import org.bouncycastle.util.encoders.Hex;

import java.util.HashMap;
import java.util.Map;

/**
 * Data container for public post encryption data.
 *
 * @author Robin Steil
 */
public class PublicPostEncryptionData implements CryptoData<PublicPostEncryptionData> {

  private static final String ENCRYPTED_BYTES = "encryptionEncryptedBytes";
  private static final String ALGORITHM = "encryptionAlgorithm";
  private static final String MODE = "encryptionMode";
  private static final String PADDING = "encryptionPadding";
  private static final String ALGORITHM_PARAMETERS_AS_BYTES =
          "encryptionAlgorithmParametersAsBytes";
  private static final String[] XMLKeys = new String[]{ENCRYPTED_BYTES,
          ALGORITHM, MODE, PADDING, ALGORITHM_PARAMETERS_AS_BYTES};
  private byte[] encryptedBytes;
  private String algorithm;
  private String mode;
  private String padding;
  private byte[] algorithmParametersAsBytes;

  /**
   * Sets member with the given parameter
   *
   * @param encryptedBytes             the encrypted bytes
   * @param transformation             the transformation
   * @param algorithmParametersAsBytes the algorithm parameters as bytes
   * @return the public post encryption data
   */
  public PublicPostEncryptionData fill(byte[] encryptedBytes,
                                       Transformation transformation,
                                       byte[] algorithmParametersAsBytes) {
    this.encryptedBytes = encryptedBytes;
    this.algorithm = transformation.getAlgorithmName();
    this.mode = transformation.getModeName();
    this.padding = transformation.getPaddingName();
    this.algorithmParametersAsBytes = algorithmParametersAsBytes;
    return this;
  }

  @Override
  public PublicPostEncryptionData fillFromMap(Map<String, String> map) {
    byte[] encryptedBytes = Hex.decode(map.get(ENCRYPTED_BYTES));
    String algorithm = map.get(ALGORITHM);
    String mode = map.get(MODE);
    String padding = map.get(PADDING);
    Transformation transformation = new Transformation(algorithm, mode,
                                                       padding);
    byte[] algorithmParametersAsBytes = Hex.decode(
            map.get(ALGORITHM_PARAMETERS_AS_BYTES));
    return fill(encryptedBytes, transformation, algorithmParametersAsBytes);
  }

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ENCRYPTED_BYTES, Hex.toHexString(encryptedBytes));
    map.put(ALGORITHM, algorithm);
    map.put(MODE, mode);
    map.put(PADDING, padding);
    map.put(ALGORITHM_PARAMETERS_AS_BYTES,
            Hex.toHexString(algorithmParametersAsBytes));
    return map;
  }

  /**
   * Get encrypted bytes as array.
   *
   * @return tencrypted bytes as array
   */
  public byte[] getEncryptedBytes() {
    return encryptedBytes;
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
   * Gets transformation.
   *
   * @return the transformation
   */
  public Transformation getTransformation() {
    return new Transformation(algorithm, mode, padding);
  }

  /**
   * Get algorithm parameters as byte array.
   *
   * @return algorithm parameters as byte array
   */
  public byte[] getAlgorithmParametersAsBytes() {
    return algorithmParametersAsBytes;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
