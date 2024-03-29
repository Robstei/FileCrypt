package edu.junit5.quickstart.password;

import org.bouncycastle.jcajce.spec.ScryptKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


/**
 * This offers the necessary functions to create a key from a password.
 *
 * @author Robin Steil
 */
public class PasswordModel {


  private PublicPasswordData publicPasswordData;


  /**
   * Generates a key for the given parameter.
   *
   * @param password  the password
   * @param algorithm the algorithm
   * @param keyLength the key length. use -1 if algorithm dictates key length
   * @return the generated key
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws NoSuchProviderException  the no such provider exception
   * @throws InvalidKeySpecException  the invalid key spec exception
   */
  public Key generateKey(char[] password, String algorithm,
                         int keyLength) throws GeneralSecurityException {
    SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT",
                                                         "BC");

    // recommendation is to have the salt at least at the size of the used
    // HMAC according to "Java Cryptography: Tools and Techniques"
    int SALT_LENGTH_IN_BIT = 256;
    byte[] salt = secureRandom.generateSeed(SALT_LENGTH_IN_BIT / 8);

    PublicPasswordData publicPasswordData = new PublicPasswordData().fill(
            algorithm,
            salt,
            keyLength);
    return generateKey(password, publicPasswordData);
  }

  /**
   * Generates a key for the given parameter.
   *
   * @param password           the password
   * @param publicPasswordData the public password data
   * @return the generated key
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws InvalidKeySpecException  the invalid key spec exception
   */
  public Key generateKey(char[] password,
                         PublicPasswordData publicPasswordData)
          throws GeneralSecurityException {
    Key key;
    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
            publicPasswordData.getAlgorithm(),
            new BouncyCastleProvider());
    if (publicPasswordData.getAlgorithm().equals("SCRYPT")) {
      //Source for parameters http://www.tarsnap.com/scrypt/scrypt-slides.pdf
      key = secretKeyFactory.generateSecret(
              new ScryptKeySpec(password,
                                publicPasswordData.getSalt(),
                                1048576,
                                8,
                                1,
                                publicPasswordData.getKeyLength()));
    } else {


      //NIST recommends at least 10.000 iterations. see https://pages.nist.gov/800-63-3/sp800-63b.html
      int ITERATION_COUNT = 10000;

      if (publicPasswordData.getKeyLength() > 0) {
        key = secretKeyFactory.generateSecret(
                new PBEKeySpec(password,
                               publicPasswordData.getSalt(), ITERATION_COUNT,
                               publicPasswordData.getKeyLength()));
      } else {
        key = secretKeyFactory.generateSecret(
                new PBEKeySpec(password,
                               publicPasswordData.getSalt(),
                               ITERATION_COUNT));
      }
    }
    this.publicPasswordData = publicPasswordData;
    return key;
  }

  /**
   * Gets public password data that were used for the last key generation.
   *
   * @return the public password data
   */
  public PublicPasswordData getPublicPasswordData() {
    return publicPasswordData;
  }
}
