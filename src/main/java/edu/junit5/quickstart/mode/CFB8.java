package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.PKCS7Padding;
import edu.junit5.quickstart.padding.ZeroBytePadding;

import java.util.ArrayList;
import java.util.Arrays;

public class CFB8 extends Mode {
  public CFB8() {
    super("CFB8",
          "CFB8",
          new ArrayList<>(
                  Arrays.asList(new NoPadding(),
                                new PKCS7Padding(),
                                new ZeroBytePadding())),
          true,
          false);
  }
}
