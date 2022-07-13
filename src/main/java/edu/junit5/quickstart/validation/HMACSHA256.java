package edu.junit5.quickstart.validation;

public class HMACSHA256 extends Validation {
    public HMACSHA256() {
        super("HMACSHA256", "HMACSHA256", ValidationType.MAC);
    }
}
