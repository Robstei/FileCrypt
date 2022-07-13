package edu.junit5.quickstart.validation;

import java.security.Key;

public class ValidationParams {

    private final String name;

    private final Key key;

    private final byte[] computedBytes;

    public ValidationParams(String name, byte[] computedBytes, Key key) {
        this.name = name;
        this.computedBytes = computedBytes;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public byte[] getComputedBytes() {
        return computedBytes;
    }

    public Key getKey() {
        return key;
    }
}
