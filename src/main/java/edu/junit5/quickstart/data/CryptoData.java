package edu.junit5.quickstart.data;

import java.util.Map;

/**
 * Base representation of secret, privat and public crypto data container.
 *
 * @param <T> the type parameter. Type Parameter should be set to the
 *            implementing class
 * @author Robin Steil
 */
public interface CryptoData<T> {

  /**
   * Gets values as map.
   *
   * @return the values of the crypto data container as map
   */
  Map<String, String> getValuesAsMap();

  /**
   * Creates a crypto data container based on a map with corresponding keys.
   *
   * @param map the map
   * @return the t
   */
  T fillFromMap(
          Map<String, String> map);

  /**
   * Gets the keys of the map representation of the crypto data container.
   *
   * @return an Array of the keys of the map representation of the crypto data
   * container
   */
  String[] getMapKeys();

}
