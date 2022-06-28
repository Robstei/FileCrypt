package edu.junit5.quickstart;

import edu.junit5.quickstart.Algorithm.AES;
import edu.junit5.quickstart.Algorithm.Algorithm;
import edu.junit5.quickstart.Mode.CBC;
import edu.junit5.quickstart.Mode.Mode;
import edu.junit5.quickstart.Padding.NoPadding;
import edu.junit5.quickstart.Padding.Padding;

public class State {
    private Algorithm algorithm;
    private int KeySize;
    private Mode mode;
    private Padding padding;
    private String path;

    final private static State state = new State();

    public static State getState() {
        return state;
    }
     private State() {
        this.algorithm = new AES();
        this.mode = new CBC();
        this.padding = new NoPadding();
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int getKeySize() {
        return KeySize;
    }

    public void setKeySize(int keySize) {
        KeySize = keySize;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}