package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.Mode;

/**
 * Representation of Bouncy Castles SHA256withDSA algorithm
 *
 * @author Robin Steil
 */
public class SHA256withDSA extends Algorithm {
  /**
   * Instantiates PBEWithSHAAnd40BitRC4 with its properties for
   * later lockups
   */
  public SHA256withDSA() {
    super("SHA256withDSA",
          "SHA256withDSA",
          new Mode[0],
          true,
          "DSA");
  }
}
