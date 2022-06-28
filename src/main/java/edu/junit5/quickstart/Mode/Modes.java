package edu.junit5.quickstart.Mode;

public class Modes {
    final private Mode[] availableModes;

    public Modes() {

        this.availableModes = new Mode[]{
                new OFB()
        };
    }
}
