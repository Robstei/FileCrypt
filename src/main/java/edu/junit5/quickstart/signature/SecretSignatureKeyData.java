package edu.junit5.quickstart.signature;

import edu.junit5.quickstart.data.CryptoData;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Data container for secret signature key data.
 *
 * @author Robin Steil
 */
public class SecretSignatureKeyData implements CryptoData<SecretSignatureKeyData> {

  private static final String ALGORITHM = "signatureAlgorithm";
  private static final String PRIVATE_KEY_AS_BYTES =
          "signaturePrivateKeyAsBytes";

  private static final String[] XMLKeys = {ALGORITHM, PRIVATE_KEY_AS_BYTES};
  private String algorithm;
  private PrivateKey privateKey;

  /**
   * Sets member with the given parameter
   *
   * @param algorithm  the algorithm
   * @param privateKey the private key
   * @return the secret signature key data
   */
  public SecretSignatureKeyData fill(String algorithm, PrivateKey privateKey) {
    this.algorithm = algorithm;
    this.privateKey = privateKey;
    return this;
  }

  @Override
  public SecretSignatureKeyData fillFromMap(Map<String, String> map) {
    try {
      String algorithm = map.get(ALGORITHM);
      byte[] privateKeyAsBytes = Hex.decode(map.get(PRIVATE_KEY_AS_BYTES));
      KeyFactory keyFactory = KeyFactory.getInstance(algorithm, "BC");
      PrivateKey privateKey = keyFactory.generatePrivate(
              new PKCS8EncodedKeySpec(privateKeyAsBytes));
      return fill(algorithm, privateKey);
    } catch (NoSuchAlgorithmException | NoSuchProviderException |
             InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ALGORITHM, algorithm);
    map.put(PRIVATE_KEY_AS_BYTES, Hex.toHexString(privateKey.getEncoded()));
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
   * Gets private key.
   *
   * @return the private key
   */
  public PrivateKey getPrivateKey() {
    return privateKey;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
