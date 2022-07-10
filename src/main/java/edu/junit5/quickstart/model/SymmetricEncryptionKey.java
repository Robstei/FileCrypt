package edu.junit5.quickstart.model;

import java.security.Key;

public class SymmetricEncryptionKey {
    private final Key key;
    private final String algorithm;
    private final String mode;
    private final String padding;
    private final byte[] iv;

    public SymmetricEncryptionKey(Key key, String algorithm, String mode, String padding, byte[] iv) {
        this.key = key;
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;
        this.iv = iv;
    }

    public SymmetricEncryptionKey(Key key, String algorithm, String mode, String padding) {
        this(key, algorithm, mode, padding, null);
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
}
