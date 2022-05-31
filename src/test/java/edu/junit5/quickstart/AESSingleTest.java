package edu.junit5.quickstart;

import org.bouncycastle.util.encoders.Hex;

public class AESSingleTest {

    private String mode;
    private String plainText;
    private String key;
    private String iv;
    private String cipherText;
    private int encryptOrDecrypt;


    public AESSingleTest(String plainText, String key, String iv,
                         String cipherText, String mode, int encryptOrDecrypt) {
        this.plainText = plainText;
        this.key = key;
        this.iv = iv;
        this.cipherText = cipherText;
        this.mode = mode;
        this.encryptOrDecrypt = encryptOrDecrypt;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPlainText() {
        return plainText;
    }

    public byte[] getPlainTextAsBytes() {
        if (plainText.length() % 2 != 0) {
            plainText = "0" + plainText;
        }
        return Hex.decode(plainText);
    }

    public String getKey() {
        return key;
    }

    public byte[] getKeyAsBytes() {
        return Hex.decode(key);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCipherText() {
        return cipherText;
    }

    public byte[] getCipherTextAsBytes() {
        return Hex.decode(cipherText);
    }

    public int getEncryptOrDecrypt() {
        return encryptOrDecrypt;
    }
    
}
