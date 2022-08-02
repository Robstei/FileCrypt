package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.PKCS7Padding;
import edu.junit5.quickstart.padding.ZeroBytePadding;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representation of ECB mode
 *
 * @author Robin Steil
 */
public class ECB extends Mode {

  /**
   * Instantiates ECB with its properties for later lockups
   */
  public ECB() {
    super("ECB", "ECB", new ArrayList<>(Arrays.asList(
            new NoPadding(),
            new PKCS7Padding(),
            new ZeroBytePadding()
    )), false, false);
  }
}
