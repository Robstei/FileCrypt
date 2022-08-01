package edu.junit5.quickstart.password;

import edu.junit5.quickstart.data.AbstractCryptoData;
import org.bouncycastle.util.encoders.Hex;

import java.util.HashMap;
import java.util.Map;

public class PublicPasswordData implements AbstractCryptoData<PublicPasswordData> {

  private static final String ALGORITHM = "passwordAlgorithm";
  private static final String SALT_AS_BYTES = "passwordSalt";
  private static final String KEY_LENGTH = "passwordKeyLength";
  private static final String[] XMLKeys = {ALGORITHM, SALT_AS_BYTES,
          KEY_LENGTH};
  private String algorithm;
  private byte[] salt;
  private int keyLength;


  public PublicPasswordData fill(String algorithm, byte[] salt, int keyLength) {
    this.algorithm = algorithm;
    this.salt = salt;
    this.keyLength = keyLength;
    return this;
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

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ALGORITHM, algorithm);
    map.put(SALT_AS_BYTES, Hex.toHexString(salt));
    map.put(KEY_LENGTH, String.valueOf(keyLength));
    return map;
  }

  @Override
  public PublicPasswordData fillFromMap(Map<String, String> map) {
    String algorithm = map.get(ALGORITHM);
    byte[] salt = Hex.decode(map.get(SALT_AS_BYTES));
    int keyLength = Integer.parseInt(map.get(KEY_LENGTH));
    return fill(algorithm, salt, keyLength);
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
