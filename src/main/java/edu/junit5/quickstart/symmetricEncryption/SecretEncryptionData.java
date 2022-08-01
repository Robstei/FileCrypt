package edu.junit5.quickstart.symmetricEncryption;

import edu.junit5.quickstart.data.AbstractCryptoData;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class SecretEncryptionData implements AbstractCryptoData<SecretEncryptionData> {

  private static final String KEY_AS_BYTES = "encryptionKeyAsBytes";
  private static final String ALGORITHM = "encryptionKeyAlgorithm";

  private static final String[] XMLKeys = {KEY_AS_BYTES, ALGORITHM};
  private Key key;

  public SecretEncryptionData fill(Key key) {
    this.key = key;
    return this;
  }

  public SecretEncryptionData fillFromMap(Map<String, String> map) {
    String encryptionAlgorithm = map.get(
            ALGORITHM);
    byte[] keyAsBytes = Hex.decode(
            map.get(KEY_AS_BYTES));
    Key key = new SecretKeySpec(keyAsBytes, encryptionAlgorithm);
    return fill(key);
  }

  public Key getKey() {
    return key;
  }

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ALGORITHM, key.getAlgorithm());
    map.put(KEY_AS_BYTES, Hex.toHexString(key.getEncoded()));
    return map;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }

}
