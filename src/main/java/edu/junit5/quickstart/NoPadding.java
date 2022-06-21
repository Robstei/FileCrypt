package edu.junit5.quickstart;

public class NoPadding extends Padding {
    
    public NoPadding(String bouncyCastleName, String UIName) {
        super(bouncyCastleName, UIName);
    }

    public boolean validate(Mode mode) {
        String[] possibleModeNames = {"OFBMode"};
        String currentMode = mode.getBouncyCastleName();
        boolean valid = false;

        for (String modeName : possibleModeNames) {
            if (currentMode.equals(modeName)) {
                valid = true;
            }
        }
        return valid;
    }
}
