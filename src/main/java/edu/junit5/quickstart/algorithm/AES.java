package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.Mode;
import edu.junit5.quickstart.mode.OFB;

public class AES extends Algorithm {

  public AES() {
    super("AES",
          "AES",
          new Mode[]{
                  new OFB()
          }, "AES");
  }
}
