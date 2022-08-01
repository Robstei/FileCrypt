package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;

import java.util.ArrayList;
import java.util.Arrays;

public class GCM extends Mode {

  public GCM() {
    super("GCM", "GCM", new ArrayList<>(Arrays.asList(
            new NoPadding()
    )), true, true);
  }
}
