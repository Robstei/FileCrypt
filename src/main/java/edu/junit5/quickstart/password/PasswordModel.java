package edu.junit5.quickstart.password;

import org.bouncycastle.jcajce.spec.ScryptKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordModel {

  // recommendation is to have the salt at least at the size of the used
  // HMAC according to "Java Cryptography: Tools and Techniques"
  private final int SALT_LENGTH_IN_BYTES = 256;
  //NIST recommends at least 10.000 iterations. see https://pages.nist.gov/800-63-3/sp800-63b.html
  private final int ITERATION_COUNT = 10000;

  private PublicPasswordData publicPasswordData;


  public Key generateKey(String password, String algorithm, int keyLength) {
    try {
      SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT",
                                                           "BC");
      byte[] salt = secureRandom.generateSeed(SALT_LENGTH_IN_BYTES);
      PublicPasswordData publicPasswordData = new PublicPasswordData().fill(
              algorithm,
              salt,
              keyLength);
      return generateKey(password, publicPasswordData);
    } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
      throw new RuntimeException(e);
    }
  }

  public Key generateKey(String password,
                         PublicPasswordData publicPasswordData) {
    try {
      Key key;
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
              publicPasswordData.getAlgorithm(),
              new BouncyCastleProvider());
      if (publicPasswordData.getAlgorithm().equals("SCRYPT")) {
        //Source for parameters http://www.tarsnap.com/scrypt/scrypt-slides.pdf
        key = secretKeyFactory.generateSecret(
                new ScryptKeySpec(password.toCharArray(),
                                  publicPasswordData.getSalt(), 1048576, 8, 1,
                                  publicPasswordData.getKeyLength()));
      } else {

        if (publicPasswordData.getKeyLength() > 0) {
          key = secretKeyFactory.generateSecret(
                  new PBEKeySpec(password.toCharArray(),
                                 publicPasswordData.getSalt(), ITERATION_COUNT,
                                 publicPasswordData.getKeyLength()));
        } else {
          key = secretKeyFactory.generateSecret(
                  new PBEKeySpec(password.toCharArray(),
                                 publicPasswordData.getSalt(),
                                 ITERATION_COUNT));
        }
      }
      //TODO: Check slides on how to properly delete plain text password after
      // usage
      password = "";
      this.publicPasswordData = publicPasswordData;
      return key;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  public PublicPasswordData getPublicPasswordData() {
    return publicPasswordData;
  }
}
