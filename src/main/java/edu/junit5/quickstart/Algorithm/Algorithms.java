package edu.junit5.quickstart.Algorithm;

public class Algorithms {

    private final Algorithm[] availableAlgorithm;

    public Algorithms() {
        this.availableAlgorithm = new Algorithm[]{
                new AES()
        };
    }
}
