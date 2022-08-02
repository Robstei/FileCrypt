package edu.junit5.quickstart;

import edu.junit5.quickstart.data.Transformation;
import org.bouncycastle.util.encoders.Hex;

public class AESSingleTest {
  private final Transformation transformation;
  private final String cipherText;
  private final int encryptOrDecrypt;
  private String plainText;
  private String key;
  private String iv;


  public AESSingleTest(String plainText, Transformation transformation,
                       String key, String iv,
                       String cipherText, int encryptOrDecrypt) {
    this.plainText = plainText;
    this.transformation = transformation;
    this.key = key;
    this.iv = iv;
    this.cipherText = cipherText;
    this.encryptOrDecrypt = encryptOrDecrypt;
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

  public void setKey(String key) {
    this.key = key;
  }

  public byte[] getKeyAsBytes() {
    return Hex.decode(key);
  }

  public String getIv() {
    return iv;
  }

  public void setIv(String iv) {
    this.iv = iv;
  }

  public byte[] getCipherTextAsBytes() {
    return Hex.decode(cipherText);
  }

  public int getEncryptOrDecrypt() {
    return encryptOrDecrypt;
  }

  public Transformation getTransformation() {
    return transformation;
  }
}
