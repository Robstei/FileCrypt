package edu.junit5.quickstart.validation;

import java.util.HashMap;

public class Validations {
    private final HashMap<String, Validation> availableValidations = new HashMap<>();

    public Validations() {
        availableValidations.put(new SHA256().getBouncyCastleName(), new SHA256());
        availableValidations.put(new AESCMAC().getBouncyCastleName(), new AESCMAC());
        availableValidations.put(new HMACSHA256().getBouncyCastleName(), new HMACSHA256());
    }

    public ValidationType getValidationType(String name) {
        return availableValidations.get(name).getValidationType();
    }
}
