package edu.junit5.quickstart.signature;

import edu.junit5.quickstart.data.CryptoData;
import org.bouncycastle.util.encoders.Hex;

import java.util.HashMap;
import java.util.Map;

/**
 * Data container for public signature data.
 *
 * @author Robin Steil
 */
public class PublicSignatureData implements CryptoData<PublicSignatureData> {

  private static final String ALGORITHM = "signatureAlgorithm";
  private static final String SIGNED_BYTES = "signatureSignedBytes";
  private static final String SIGNATURE = "signatureSignature";
  private static final String[] XMLKeys = {ALGORITHM, SIGNED_BYTES, SIGNATURE};
  private String algorithm;
  private byte[] signedBytes;
  private byte[] signature;

  /**
   * Sets member with the given parameter
   *
   * @param algorithm   the algorithm
   * @param signedBytes the signed bytes
   * @param signature   the signature
   * @return the public signature data
   */
  public PublicSignatureData fill(String algorithm, byte[] signedBytes,
                                  byte[] signature) {
    this.algorithm = algorithm;
    this.signedBytes = signedBytes;
    this.signature = signature;
    return this;
  }

  @Override
  public PublicSignatureData fillFromMap(Map<String, String> map) {
    String algorithm = map.get(ALGORITHM);
    byte[] signedBytes = Hex.decode(map.get(SIGNED_BYTES));
    byte[] signature = Hex.decode(map.get(SIGNATURE));
    return fill(algorithm, signedBytes, signature);
  }

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ALGORITHM, algorithm);
    map.put(SIGNED_BYTES, Hex.toHexString(signedBytes));
    map.put(SIGNATURE, Hex.toHexString(signature));
    return map;
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
   * Get signed bytes as array.
   *
   * @return signed bytes as array
   */
  public byte[] getSignedBytes() {
    return signedBytes;
  }

  /**
   * Get signature as bytes array.
   *
   * @return signature as bytes array
   */
  public byte[] getSignature() {
    return signature;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
