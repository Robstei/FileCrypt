package edu.junit5.quickstart.Padding;

import java.util.ArrayList;

public abstract class Padding {

    private static ArrayList<Padding> activePaddings = new ArrayList<>();
    private String bouncyCastleName;
    private String UIName;

    private Padding instance;

    public Padding(String bouncyCastleName, String UIName) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
        this.instance = this;
        activePaddings.add(this);
    }

    public Padding getInstance() {
        return this.instance;
    }

    public static ArrayList<Padding> getActivePaddings() {
        return activePaddings;
    }

    public String getBouncyCastleName() {
        return bouncyCastleName;
    }

    public String getUIName() {
        return UIName;
    }
}
