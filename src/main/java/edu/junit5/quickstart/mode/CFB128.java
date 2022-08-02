package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.PKCS7Padding;
import edu.junit5.quickstart.padding.ZeroBytePadding;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representation of CFB128 mode
 *
 * @author Robin Steil
 */
public class CFB128 extends Mode {

  /**
   * Instantiates CFB128 with its properties for later lockups
   */
  public CFB128() {
    super("CFB128",
          "CFB128",
          new ArrayList<>(
                  Arrays.asList(new NoPadding(),
                                new PKCS7Padding(),
                                new ZeroBytePadding())),
          true,
          false);
  }
}
