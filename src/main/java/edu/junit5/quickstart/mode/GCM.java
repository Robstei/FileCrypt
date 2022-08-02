package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of GCM mode
 *
 * @author Robin Steil
 */
public class GCM extends Mode {

  /**
   * Instantiates GCM with its properties for later lockups
   */
  public GCM() {
    super("GCM", "GCM", new ArrayList<>(List.of(
            new NoPadding()
    )), true, true);
  }
}
