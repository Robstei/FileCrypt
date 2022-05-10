package edu.junit5.quickstart;

public enum Algorithm implements ConfigEnum {
    AES("AES", "AES (Advanced Encryption Standard)");

    String name;
    String UIName;

    Algorithm(String name, String UIName) {
        this.name = name;
        this.UIName = UIName;
    }
}
