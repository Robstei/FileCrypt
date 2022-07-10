package edu.junit5.quickstart.mode;

import edu.junit5.quickstart.padding.NoPadding;
import edu.junit5.quickstart.padding.Padding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Mode {

    private String bouncyCastleName;
    private String UIName;
    private boolean needsIV;

    final private ArrayList<Padding> possiblePaddings;

    Mode(String bouncyCastleName, String UIName, boolean needsIV,
         ArrayList<Padding> possiblePaddings) {
        this.bouncyCastleName = bouncyCastleName;
        this.UIName = UIName;
        this.needsIV = needsIV;
        this.possiblePaddings = possiblePaddings;
    }

    public String getBouncyCastleName() {
        return bouncyCastleName;
    }

    public boolean isValidPadding(String key) {
        for(Padding padding : possiblePaddings) {
            if(padding.getBouncyCastleName().equals("key")) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getValidPaddingNames() {
        return possiblePaddings.stream().map(padding -> padding.getBouncyCastleName()).collect(Collectors.toCollection(ArrayList::new));
    }
}