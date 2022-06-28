package edu.junit5.quickstart.Mode;

import edu.junit5.quickstart.Padding.NoPadding;
import edu.junit5.quickstart.Padding.Padding;

public abstract class Mode {

    private String bouncyCastleName;
    private String UIName;
    private boolean needsIV;

    final private Padding[] possiblePaddings;

    Mode(String bouncyCastleName, String UIName, boolean needsIV,
         Padding[] possiblePaddings) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
        this.needsIV = needsIV;
        this.possiblePaddings = new Padding[]{new NoPadding()};
    }

    public String getBouncyCastleName() {
        return bouncyCastleName;
    }

    public String getUIName() {
        return UIName;
    }

    public boolean isNeedsIV() {
        return needsIV;
    }

}