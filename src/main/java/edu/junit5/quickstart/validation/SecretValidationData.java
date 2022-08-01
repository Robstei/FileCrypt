package edu.junit5.quickstart.validation;

import edu.junit5.quickstart.data.AbstractCryptoData;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class SecretValidationData implements AbstractCryptoData<SecretValidationData> {

  private static final String ALGORITHM = "validationAlgorithm";
  private static final String KEY_AS_BYTES = "validationKeyAsBytes";
  private static final String[] XMLKeys = {ALGORITHM, KEY_AS_BYTES};
  private Key key;

  public SecretValidationData fill(Key key) {
    this.key = key;
    return this;
  }

  @Override
  public SecretValidationData fillFromMap(Map<String, String> map) {
    String algorithm = map.get(ALGORITHM);
    byte[] keyAsBytes = Hex.decode(map.get(KEY_AS_BYTES));
    Key key = new SecretKeySpec(keyAsBytes, algorithm);
    return fill(key);
  }

  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(ALGORITHM, key.getAlgorithm());
    map.put(KEY_AS_BYTES, Hex.toHexString(key.getEncoded()));
    return map;
  }

  public Key getKey() {
    return key;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
