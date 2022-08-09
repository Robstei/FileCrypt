package edu.junit5.quickstart.symmetricEncryption;

import edu.junit5.quickstart.data.Transformation;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;

/**
 * The class handles all the functionality in regard to symmetric encryption
 * and decryption.
 *
 * @author Robin Steil
 */
public class SymmetricEncryptionModel {
  private byte[] result;
  private PublicPostEncryptionData publicPostEncryptionData;
  private SecretEncryptionData secretEncryptionData;

  /**
   * Gets the encryption result as byte array.
   *
   * @return the encryption result as byte array
   */
  public byte[] getResult() {
    return result;
  }

  /**
   * Gets public encryption data.
   *
   * @return the public encryption data
   */
  public PublicPostEncryptionData getPublicPostEncryptionData() {
    return publicPostEncryptionData;
  }

  /**
   * Gets secret encryption data.
   *
   * @return the secret encryption data
   */
  public SecretEncryptionData getSecretEncryptionData() {
    return secretEncryptionData;
  }

  /**
   * Setup symmetric encryption only with public pre encryption data, without
   * a key.
   *
   * @param publicPreEncryptionData the public pre encryption data
   * @throws GeneralSecurityException the general security exception
   * @throws IOException              the io exception
   */
  public void manageSymmetricEncryption(
          PublicPreEncryptionData publicPreEncryptionData) throws
          GeneralSecurityException, IOException {
    manageSymmetricEncryption(publicPreEncryptionData, null);
  }

  /**
   * Setup symmetric encryption with public pre encryption data and a key.
   *
   * @param publicPreEncryptionData the public pre encryption data
   * @param providedKey             the provided key
   * @throws GeneralSecurityException the general security exception
   * @throws IOException              the io exception
   */
  public void manageSymmetricEncryption(
          PublicPreEncryptionData publicPreEncryptionData,
          Key providedKey) throws GeneralSecurityException, IOException {
    Key key = providedKey;
    //generate key if key is null
    if (key == null) {
      KeyGenerator keyGenerator =
              KeyGenerator.getInstance(
                      publicPreEncryptionData.getAlgorithm(),
                      "BC");
      keyGenerator.init(publicPreEncryptionData.getKeyLength());
      key = keyGenerator.generateKey();
    }


    String algorithmForParameterGeneration =
            publicPreEncryptionData.getTransformation().getNameForParameterGeneration();
    AlgorithmParameters algorithmParameters = null;
    if (algorithmForParameterGeneration != null) {
      AlgorithmParameterGenerator algorithmParameterGenerator =
              AlgorithmParameterGenerator.getInstance(
                      algorithmForParameterGeneration,
                      "BC");
      algorithmParameters =
              algorithmParameterGenerator.generateParameters();
    }

    result = encryptSymmetric(publicPreEncryptionData.getBytesToEncrypt(),
                              publicPreEncryptionData.getTransformation(),
                              key, algorithmParameters);

    byte[] algorithmParametersAsBytes = algorithmParameters != null ?
            algorithmParameters.getEncoded() : new byte[0];

    this.publicPostEncryptionData = new PublicPostEncryptionData().fill(
            result,
            publicPreEncryptionData.getTransformation(),
            algorithmParametersAsBytes
    );
    this.secretEncryptionData = new SecretEncryptionData().fill(key);
  }

  /**
   * Setup symmetric decryption by generating low level paramter from
   * provided data containers.
   *
   * @param publicPostEncryptionData the public post encryption data
   * @param secretEncryptionData     the secret encryption data
   * @throws GeneralSecurityException the general security exception
   * @throws IOException              the io exception
   */
  public void manageSymmetricDecryption(
          PublicPostEncryptionData publicPostEncryptionData,
          SecretEncryptionData secretEncryptionData)
          throws GeneralSecurityException, IOException {

    String nameForParameterGeneration =
            publicPostEncryptionData.getTransformation().getNameForParameterGeneration();
    AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(
            nameForParameterGeneration,
            "BC");
    algorithmParameters.init(
            publicPostEncryptionData.getAlgorithmParametersAsBytes());
    result = decryptSymmetric(
            publicPostEncryptionData.getEncryptedBytes(),
            publicPostEncryptionData.getTransformation(),
            secretEncryptionData.getKey(),
            algorithmParameters);
  }

  /**
   * Encrypt the input symmetrically with given parameters.
   *
   * @param input               the input
   * @param transformation      the transformation
   * @param key                 the key
   * @param algorithmParameters the algorithm parameters
   * @return the byte [ ]
   * @throws GeneralSecurityException the general security exception
   */
  public byte[] encryptSymmetric(byte[] input, Transformation transformation,
                                 Key key,
                                 AlgorithmParameters algorithmParameters)
          throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance(transformation.toString(),
                                       "BC");
    cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameters);
    return cipher.doFinal(input);
  }

  /**
   * Decrypt the encrypted bytes symmetrically with given parameters.
   *
   * @param encryptedBytes      the encrypted bytes
   * @param transformation      the transformation
   * @param key                 the key
   * @param algorithmParameters the algorithm parameters
   * @return the decrypted bytes
   * @throws GeneralSecurityException the general security exception
   */
  public byte[] decryptSymmetric(byte[] encryptedBytes,
                                 Transformation transformation, Key key,
                                 AlgorithmParameters algorithmParameters) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance(transformation.toString(),
                                       "BC");
    cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
    return cipher.doFinal(encryptedBytes);
  }
}
