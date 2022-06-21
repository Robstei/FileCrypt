package edu.junit5.quickstart;

import java.security.Key;

abstract public class Executor {

    private Key key;
    private String iv;
    private byte[] clearText;
    private byte[] cipherText;
    private String algorithm;
    private String mode;
    private String padding;

//    public Executor(Key key, String iv, byte[] clearText, byte[] cipherText,
//                    String algorithm, String mode, String padding) {
//        this.key = key;
//        this.iv = iv;
//        this.clearText = clearText;
//        this.cipherText = cipherText;
//        this.algorithm = algorithm;
//        this.mode = mode;
//        this.padding = padding;
//    }

    public void init(Key key, String iv, byte[] clearText, byte[] cipherText,
                     String algorithm, String mode, String padding) {
        this.key = key;
        this.iv = iv;
        this.clearText = clearText;
        this.cipherText = cipherText;
        this.algorithm = algorithm;
        this.mode = mode;
        this.padding = padding;
    }

    abstract public void encrypt();

    abstract public void decrypt();

    public byte[] getClearText() {
        return this.clearText;
    }

    public byte[] getCipherText() {
        return this.cipherText;
    }

    abstract void createClearTextAsFile();

    abstract void createCipherTextAsFile();

    abstract void createEncryptionMetaDataFile();
}