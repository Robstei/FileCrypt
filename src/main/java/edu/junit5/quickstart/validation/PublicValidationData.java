package edu.junit5.quickstart.validation;

import edu.junit5.quickstart.model.AbstractCryptoData;
import org.bouncycastle.util.encoders.Hex;

import java.util.HashMap;
import java.util.Map;

public class PublicValidationData implements AbstractCryptoData<PublicValidationData> {
  private static final String NAME = "validationName";
  private static final String COMPUTED_BYTES = "validationComputedBytes";
  private static final String[] XMLKeys = {NAME, COMPUTED_BYTES};
  private String name;
  private byte[] computedBytes;

  public PublicValidationData fill(String name, byte[] computedBytes) {
    this.name = name;
    this.computedBytes = computedBytes;
    return this;
  }

  @Override
  public PublicValidationData fillFromMap(Map<String, String> map) {
    String name = map.get(NAME);
    byte[] computedBytes = Hex.decode(map.get(COMPUTED_BYTES));
    return fill(name, computedBytes);
  }


  @Override
  public Map<String, String> getValuesAsMap() {
    Map<String, String> map = new HashMap<>();
    map.put(NAME, name);
    map.put(COMPUTED_BYTES, Hex.toHexString(computedBytes));
    return map;
  }

  public String getName() {
    return name;
  }

  public byte[] getComputedBytes() {
    return computedBytes;
  }
  
  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
