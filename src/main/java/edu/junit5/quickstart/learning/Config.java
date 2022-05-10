package edu.junit5.quickstart.learning;

public class Config {

    enum Padding {
        ZeroPadding, PCS5;

        String UIName;

    }

    enum Mode {
        CPC, CTS;

        Padding[] paddings;

    }

    enum Algorithm {
       /* DES(new Mode[2] = {CPC, CTS}), AES(CTS);
        Mode[] modes;

        Algorithm(Mode[] modes) {
            modes = modes;
        }

        */
    }
}
