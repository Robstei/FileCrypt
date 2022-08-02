package edu.junit5.quickstart.validation;

import edu.junit5.quickstart.data.CryptoData;
import org.bouncycastle.util.encoders.Hex;

import java.util.HashMap;
import java.util.Map;

/**
 * Data container for public validation data.
 *
 * @author Robin Steil
 */
public class PublicValidationData implements CryptoData<PublicValidationData> {
  private static final String NAME = "validationName";
  private static final String COMPUTED_BYTES = "validationComputedBytes";
  private static final String[] XMLKeys = {NAME, COMPUTED_BYTES};
  private String name;
  private byte[] computedBytes;

  /**
   * Sets member with the given parameter
   *
   * @param name          the name
   * @param computedBytes the computed bytes
   * @return the public validation data
   */
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

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Get computed bytes as array.
   *
   * @return computed bytes as array
   */
  public byte[] getComputedBytes() {
    return computedBytes;
  }

  @Override
  public String[] getMapKeys() {
    return XMLKeys;
  }
}
