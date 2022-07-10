package edu.junit5.quickstart.padding;

public class Paddings {

    final private Padding[] availablePaddings;

    public Paddings() {
        this.availablePaddings = new Padding[]{
                new NoPadding(),
                new PKCS7Padding(),
                new ZeroBytePadding(),
                new CTS(),
        };
    }
}
