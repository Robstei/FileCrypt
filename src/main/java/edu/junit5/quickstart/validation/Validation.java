package edu.junit5.quickstart.validation;

public abstract class Validation {

    private String bouncyCastleName;
    private String UIName;

    private ValidationType validationType;

    public Validation(String bouncyCastleName, String UIName, ValidationType validationType) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
        this.validationType = validationType;
    }

    public String getBouncyCastleName() {
        return bouncyCastleName;
    }

    public String getUIName() {
        return UIName;
    }

    public ValidationType getValidationType() {
        return validationType;
    }
}
