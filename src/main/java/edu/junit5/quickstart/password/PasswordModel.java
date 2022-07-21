package edu.junit5.quickstart.password;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class PasswordModel {

  // recommendation is to have the salt at least at the size of the used
  // HMAC according to "Java Cryptography: Tools and Techniques"
  private final int SALTLENGTHINBYTES = 256;
  //NIST recommends at least 10.000 iterations. see https://pages.nist.gov/800-63-3/sp800-63b.html
  private final int ITERATIONCOUNT = 10000;


  private PublicPasswordData publicPasswordData;

  public Key generateKey(String password, String algorithm) {
    try {
      SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT",
                                                           new BouncyCastleProvider());
      byte[] salt = secureRandom.generateSeed(SALTLENGTHINBYTES);
      return generateKey(password, algorithm, salt);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public Key generateKey(String password, String algorithm, byte[] salt) {
    try {
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
              algorithm,
              new BouncyCastleProvider());


      Key key = secretKeyFactory.generateSecret(
              new PBEKeySpec(password.toCharArray(), salt, ITERATIONCOUNT));
      //TODO: Check slides on how to properly delete plain text password after
      // usage
      password = "";
      publicPasswordData = new PublicPasswordData(salt);
      return key;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  public void manageEncryptWithPassword(String algorithm, String password) {
    boolean cipher = isCipher(algorithm);
    if (cipher) {
      encryptWithPasswordCipher(algorithm, password);
    } else {
      encryptWithSecretKeyFactory(algorithm, password);
    }
  }

  private void encryptWithSecretKeyFactory(String algorithm, String password) {

  }

  private void encryptWithPasswordCipher(String algorithm, String password) {
    try {
      Cipher cipher = Cipher.getInstance(
              algorithm,
              new BouncyCastleProvider());
      Key key = new SecretKeySpec(Hex.decode(password),
                                  algorithm);
      cipher.init(Cipher.ENCRYPT_MODE, key);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException |
             InvalidKeyException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isCipher(String algorithm) {
    boolean cipher = false;

    for (Provider.Service service : new BouncyCastleProvider().getServices()) {
      if (service.getClassName().equals(
              algorithm) && service.getType().equals(
              "Cipher")) {
        cipher = true;
      }
    }
    return cipher;
  }

  public void decrypt() {

  }

  public PublicPasswordData getPublicPasswordData() {
    return publicPasswordData;
  }
}
