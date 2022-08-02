package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.PKCS7Padding;
import edu.junit5.quickstart.padding.ZeroBytePadding;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representation of CFB8 mode
 *
 * @author Robin Steil
 */
public class CFB8 extends Mode {

  /**
   * Instantiates CFB8 with its properties for later lockups
   */
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
