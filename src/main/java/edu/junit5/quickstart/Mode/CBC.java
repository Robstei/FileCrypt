package edu.junit5.quickstart.Mode;

import edu.junit5.quickstart.Padding.NoPadding;
import edu.junit5.quickstart.Padding.Padding;

public class CBC extends Mode{
    public CBC() {
        super("CBC","CBC",false, new Padding[]{
                new NoPadding()
        });
    }
}
