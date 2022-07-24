package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.PKCS7Padding;
import edu.junit5.quickstart.padding.ZeroBytePadding;

import java.util.ArrayList;
import java.util.Arrays;

public class ECB extends Mode {
  public ECB() {
    super("ECB", "ECB", false, new ArrayList<>(Arrays.asList(
            new NoPadding(),
            new PKCS7Padding(),
            new ZeroBytePadding()
    )));
  }
}
