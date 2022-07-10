package edu.junit5.quickstart.algorithm;

public class Algorithms {

    private final Algorithm[] availableAlgorithm;

    public Algorithms() {
        this.availableAlgorithm = new Algorithm[]{
                new AES()
        };
    }
}
