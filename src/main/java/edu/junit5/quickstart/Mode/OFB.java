package edu.junit5.quickstart.Mode;

import edu.junit5.quickstart.Padding.NoPadding;
import edu.junit5.quickstart.Padding.Padding;

public class OFB extends Mode {

    public OFB() {
        super("OFB", "OFB", true, new Padding[]{new NoPadding()} );
    }
}
