package edu.junit5.quickstart.state;

public class Transformation {
    private final String algorithm;
    private final String mode;
    private final String padding;

    public Transformation(String algorithm, String mode, String padding) {
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;
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

    @Override
    public String toString() {
        String string = algorithm;
        if (!mode.equals("")) {
            string += "/" + mode;
        }
        if (!padding.equals("")) {
            string += "/" + padding;
        }
        return string;
    }
}
