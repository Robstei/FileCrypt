package edu.junit5.quickstart.mode;

import java.util.HashMap;

/**
 * Registry of all modes implemented in FileCrypt.
 *
 * @author Robin Steil
 */
public class Modes {
  final private HashMap<String, Mode> availableModes = new HashMap<>();

  /**
   * Instance with reference to every implemented encryption mode
   */
  public Modes() {
    availableModes.put(new ECB().getBouncyCastleName(), new ECB());
    availableModes.put(new CBC().getBouncyCastleName(), new CBC());
    availableModes.put(new OFB().getBouncyCastleName(), new OFB());
    availableModes.put(new GCM().getBouncyCastleName(), new GCM());
    availableModes.put(new CFB128().getBouncyCastleName(), new CFB128());
    availableModes.put(new CFB8().getBouncyCastleName(), new CFB8());
  }

  /**
   * Gets mode by name.
   *
   * @param name the name
   * @return instance of the mode with the name
   */
  public Mode getModeByName(String name) {
    return availableModes.get(name);
  }
}
