package edu.junit5.quickstart.Padding;

public class Paddings {

    final private Padding[] availablePaddings;

    public Paddings() {
        this.availablePaddings = new Padding[]{
                new NoPadding()
        };
    }
}
