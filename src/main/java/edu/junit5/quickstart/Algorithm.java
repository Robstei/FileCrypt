package edu.junit5.quickstart;

public class Algorithm {

    private String bouncyCastleName;
    private String UIName;
    private Mode[] possibleModes;

    public Algorithm(String bouncyCastleName, String UIName,
                     Mode[] possibleModes) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
        this.possibleModes = possibleModes;
    }

    public String getBouncyCastleName() {
        return bouncyCastleName;
    }

    public String getUIName() {
        return UIName;
    }

    public Mode[] getPossibleModes() {
        return possibleModes;
    }
}



