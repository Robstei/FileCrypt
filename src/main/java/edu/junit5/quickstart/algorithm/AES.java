package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.*;

/**
 * Representation of AES algorithm
 *
 * @author Robin Steil
 */
public class AES extends Algorithm {

  /**
   * Instantiates AES with its properties for later lockups
   */
  public AES() {
    super("AES",
          "AES",
          new Mode[]{
                  new ECB(),
                  new CBC(),
                  new OFB(),
                  new GCM(),
          }, true, "AES");
  }
}
