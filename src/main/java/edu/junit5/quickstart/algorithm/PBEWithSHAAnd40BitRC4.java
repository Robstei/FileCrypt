package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.Mode;

/**
 * Representation of Bouncy Castles PBEWithSHAAnd40BitRC4 algorithm
 *
 * @author Robin Steil
 */
public class PBEWithSHAAnd40BitRC4 extends Algorithm {
  /**
   * Instantiates PBEWithSHAAnd40BitRC4 with its properties for
   * later lockups
   */
  public PBEWithSHAAnd40BitRC4() {
    super("PBEWithSHAAnd40BitRC4",
          "PBEWithSHAAnd40BitRC4",
          new Mode[0], false,
          null);
  }
}
