package edu.junit5.quickstart.padding;

public abstract class Padding {

    private String bouncyCastleName;
    private String UIName;
    
    public Padding(String bouncyCastleName, String UIName) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
    }

    public String getBouncyCastleName() {
        return bouncyCastleName;
    }

    public String getUIName() {
        return UIName;
    }
}
