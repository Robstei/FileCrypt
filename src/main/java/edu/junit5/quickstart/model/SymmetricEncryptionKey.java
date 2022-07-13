package edu.junit5.quickstart.model;

import edu.junit5.quickstart.validation.ValidationParams;

import java.security.Key;

public class SymmetricEncryptionKey {
    private final Key key;
    private final String algorithm;
    private final String mode;
    private final String padding;
    private final ValidationParams validationParams;
    private final byte[] iv;


    public SymmetricEncryptionKey(Key key, String algorithm, String mode, String padding, ValidationParams validationParams, byte[] iv) {
        this.key = key;
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;
        this.validationParams = validationParams;
        this.iv = iv;
    }

    public SymmetricEncryptionKey(Key key, String algorithm, String mode, String padding, ValidationParams validationParams) {
        this(key, algorithm, mode, padding, validationParams, null);
    }

    public Key getKey() {
        return key;
    }


    public byte[] getIv() {
        return iv;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getMode() {
        return mode;
    }

    public String getPadding() {
        return padding;
    }

    public ValidationParams getValidationParams() {
        return validationParams;
    }
}
