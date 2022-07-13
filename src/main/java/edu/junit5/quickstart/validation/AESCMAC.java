package edu.junit5.quickstart.validation;

public class AESCMAC extends Validation {
    public AESCMAC() {
        super("AESCMAC", "AESCMAC", ValidationType.MAC);
    }
}
