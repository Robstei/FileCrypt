package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;

import java.util.ArrayList;
import java.util.Arrays;

public class OFB extends Mode {

  public OFB() {
    super("OFB", "OFB", new ArrayList<>(Arrays.asList(
            new NoPadding()
    )), true, false);
  }
}
