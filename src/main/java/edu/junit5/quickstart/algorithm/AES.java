package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.*;

public class AES extends Algorithm {

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
