package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.CTS;
import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.Padding;

import java.util.ArrayList;
import java.util.Arrays;

public class CBC extends Mode{
    public CBC() {
        super("CBC","CBC",false, new ArrayList<>(Arrays.asList(
                new NoPadding(),
                new CTS()
        )));
    }
}