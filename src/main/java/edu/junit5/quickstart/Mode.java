package edu.junit5.quickstart;

public class Mode {

    private String bouncyCastleName;
    private String UIName;
    private boolean needsIV;
    private boolean needsPadding;

    Mode(String bouncyCastleName, String UIName, boolean needsIV,
         boolean needsPadding) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
        this.needsIV = needsIV;
        this.needsPadding = needsPadding;
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

    public boolean isNeedsPadding() {
        return needsPadding;
    }
}