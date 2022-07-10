package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.Padding;

import java.util.ArrayList;
import java.util.Arrays;

public class ECB extends Mode {
    ECB() {
        super("ECB", "ECB", false, new ArrayList<>(Arrays.asList(
                new NoPadding()
        )));
    }
}
