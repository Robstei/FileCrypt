package edu.junit5.quickstart.signature;

import edu.junit5.quickstart.algorithm.Algorithms;
import edu.junit5.quickstart.data.CryptoData;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Data container for public signature key data.
 *
 * @author Robin Steil
 */
public class PublicSignatureKeyData implements CryptoData<PublicSignatureKeyData> {
  private static final String ALGORITHM = "signatureAlgorithm";
  private static final String PUBLIC_KEY_AS_BYTES =
          "signaturePublicKeyAsBytes";
  private static final String[] XMLKeys = {ALGORITHM, PUBLIC_KEY_AS_BYTES};
  private String algorithm;
  private PublicKey publicKey;

  /**
   * Sets member with the given parameter
   *
   * @param algorithm the algorithm
   * @param publicKey the public key
   * @return the public signature key data
   */
  public PublicSignatureKeyData fill(String algorithm, PublicKey publicKey) {
    this.algorithm = algorithm;
    this.publicKey = publicKey;
    return this;
  }

  @Override
  public PublicSignatureKeyData fillFromMap(Map<String, String> map) {
    try {
      String algorithm = map.get(ALGORITHM);
      byte[] publicKeyAsBytes = Hex.decode(map.get(PUBLIC_KEY_AS_BYTES));
      String nameForParameterGeneration =
              new Algorithms().getNameForParameterGeneration(
                      algorithm);
      KeyFactory keyFactory = KeyFactory.getInstance(nameForParameterGeneration,
                                                     "BC");
      PublicKey publicKey = keyFactory.generatePublic(
              new X509EncodedKeySpec(publicKeyAsBytes));
      return fill(algorithm, publicKey);
    } catch (NoSuchAlgorithmException | NoSuchProviderException |
             InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ALGORITHM, algorithm);
    map.put(PUBLIC_KEY_AS_BYTES, Hex.toHexString(publicKey.getEncoded()));
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
   * Gets public key.
   *
   * @return the public key
   */
  public PublicKey getPublicKey() {
    return publicKey;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
