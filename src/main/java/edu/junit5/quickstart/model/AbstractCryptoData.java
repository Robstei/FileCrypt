package edu.junit5.quickstart.model;

import java.util.Map;

public interface AbstractCryptoData<T> {

  Map<String, String> getValuesAsMap();

  T fillFromMap(
          Map<String, String> map);

  String[] getMapKeys();


}
