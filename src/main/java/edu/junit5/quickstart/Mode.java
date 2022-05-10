package edu.junit5.quickstart;

public enum Mode implements ConfigEnum {

    CBC("ECB", "(ECB) Electronic Code Book");

    String name;
    String UIName;

    Mode(String name, String UIName) {
        this.name = name;
        this.UIName = UIName;
    }
}
