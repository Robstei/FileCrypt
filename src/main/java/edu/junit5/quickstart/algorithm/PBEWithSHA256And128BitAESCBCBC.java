package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.Mode;

/**
 * Representation of Bouncy Castles PBEWithSHA256And128BitAES-CBC-BC algorithm
 *
 * @author Robin Steil
 */
public class PBEWithSHA256And128BitAESCBCBC extends Algorithm {
  /**
   * Instantiates PBEWithSHA256And128BitAESCBCBC with its properties for
   * later lockups
   */
  public PBEWithSHA256And128BitAESCBCBC() {
    super("PBEWithSHA256And128BitAES-CBC-BC",
          "PBEWithSHA256And128BitAES-CBC-BC",
          new Mode[0],
          true, "AES");
  }
}
