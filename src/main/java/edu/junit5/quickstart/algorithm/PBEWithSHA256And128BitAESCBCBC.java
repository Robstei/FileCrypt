package edu.junit5.quickstart.algorithm;

import edu.junit5.quickstart.mode.Mode;

public class PBEWithSHA256And128BitAESCBCBC extends Algorithm {
  public PBEWithSHA256And128BitAESCBCBC() {
    super("PBEWithSHA256And128BitAES-CBC-BC",
          "PBEWithSHA256And128BitAES-CBC-BC",
          new Mode[0],
          true, "AES");
  }
}
