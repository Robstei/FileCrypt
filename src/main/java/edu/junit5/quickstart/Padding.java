package edu.junit5.quickstart;

public enum Padding implements ConfigEnum {

    ISO7816_4Padding("ISO7816-4Padding", "ISO7816-4Padding");
    String name;
    String UIName;


    Padding(String name, String UIName) {
        this.name = name;
        this.UIName = UIName;
    }
}
