package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of OFB mode
 *
 * @author Robin Steil
 */
public class OFB extends Mode {

  /**
   * Instantiates OFB with its properties for later lockups
   */
  public OFB() {
    super("OFB", "OFB", new ArrayList<>(List.of(
            new NoPadding()
    )), true, false);
  }
}
