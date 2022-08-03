package edu.junit5.quickstart;

import edu.junit5.quickstart.data.Transformation;
import org.bouncycastle.util.encoders.Hex;

/**
 * The type Aes single test.
 *
 * @author Robin Steil
 */
public class AESSingleTest {
  private final Transformation transformation;
  private final String cipherText;
  private final int encryptOrDecrypt;
  private final String key;
  private final String iv;
  private String plainText;


  /**
   * Instantiates a new Aes single test.
   *
   * @param plainText        the plain text
   * @param transformation   the transformation
   * @param key              the key
   * @param iv               the iv
   * @param cipherText       the cipher text
   * @param encryptOrDecrypt the encrypt or decrypt
   */
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

  /**
   * Get plain text as bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getPlainTextAsBytes() {
    if (plainText.length() % 2 != 0) {
      plainText = "0" + plainText;
    }
    return Hex.decode(plainText);
  }

  /**
   * Get key as bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getKeyAsBytes() {
    return Hex.decode(key);
  }

  /**
   * Gets iv.
   *
   * @return the iv
   */
  public String getIv() {
    return iv;
  }

  /**
   * Get cipher text as bytes byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getCipherTextAsBytes() {
    return Hex.decode(cipherText);
  }

  /**
   * Gets encrypt or decrypt.
   *
   * @return the encrypt or decrypt
   */
  public int getEncryptOrDecrypt() {
    return encryptOrDecrypt;
  }

  /**
   * Gets transformation.
   *
   * @return the transformation
   */
  public Transformation getTransformation() {
    return transformation;
  }
}
