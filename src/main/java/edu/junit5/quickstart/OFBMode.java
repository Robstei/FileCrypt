package edu.junit5.quickstart;

public class OFBMode extends Mode {

    OFBMode(String bouncyCastleName, String UIName, boolean needsIV,
            boolean needsPadding) {
        super(bouncyCastleName, UIName, needsIV, needsPadding
        );
    }
}
