package edu.junit5.quickstart.mode;

import java.util.HashMap;

public class Modes {
  final private HashMap<String, Mode> availableModes = new HashMap<>();

  public Modes() {
    availableModes.put(new ECB().getBouncyCastleName(), new ECB());
    availableModes.put(new CBC().getBouncyCastleName(), new CBC());
    availableModes.put(new OFB().getBouncyCastleName(), new OFB());
    availableModes.put(new GCM().getBouncyCastleName(), new GCM());
    availableModes.put(new CFB128().getBouncyCastleName(), new CFB128());
    availableModes.put(new CFB8().getBouncyCastleName(), new CFB8());
  }

  ;

  public Mode getModeByName(String name) {
    return availableModes.get(name);
  }
}
