package edu.junit5.quickstart.symmetricEncryption;

import edu.junit5.quickstart.data.OperationResult;
import edu.junit5.quickstart.data.Transformation;

import javax.crypto.*;
import java.io.IOException;
import java.security.*;

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
  public PublicPostEncryptionData getPublicEncryptionData() {
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
   */
  public void manageSymmetricEncryption(
          PublicPreEncryptionData publicPreEncryptionData) throws NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    manageSymmetricEncryption(publicPreEncryptionData, null);
  }

  /**
   * Setup symmetric encryption with public pre encryption data and a key.
   *
   * @param publicPreEncryptionData the public pre encryption data
   * @param providedKey             the provided key
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws IOException              the io exception
   * @throws NoSuchProviderException  the no such provider exception
   */
  public void manageSymmetricEncryption(
          PublicPreEncryptionData publicPreEncryptionData,
          Key providedKey) throws NoSuchAlgorithmException, IOException,
          NoSuchProviderException, InvalidAlgorithmParameterException,
          NoSuchPaddingException, IllegalBlockSizeException,
          BadPaddingException, InvalidKeyException {
    Key key = providedKey;
    //generate key if key is null
    if (key == null) {
      KeyGenerator keyGenerator =
              KeyGenerator.getInstance(
                      publicPreEncryptionData.getAlgorithm(),
                      "BC");
      keyGenerator.init(publicPreEncryptionData.getKeySize());
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
   * @return the operation result
   * @throws NoSuchAlgorithmException           the no such algorithm exception
   * @throws IOException                        the io exception
   * @throws NoSuchProviderException            the no such provider exception
   * @throws InvalidAlgorithmParameterException the invalid algorithm
   *                                            parameter exception
   * @throws NoSuchPaddingException             the no such padding exception
   * @throws IllegalBlockSizeException          the illegal block size exception
   * @throws BadPaddingException                the bad padding exception
   * @throws InvalidKeyException                the invalid key exception
   */
  public OperationResult manageSymmetricDecryption(
          PublicPostEncryptionData publicPostEncryptionData,
          SecretEncryptionData secretEncryptionData) throws NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
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
    return new OperationResult();
  }

  /**
   * Encrypt the input symmetrically with given parameters.
   *
   * @param input               the input
   * @param transformation      the transformation
   * @param key                 the key
   * @param algorithmParameters the algorithm parameters
   * @return the byte [ ]
   * @throws NoSuchAlgorithmException           the no such algorithm exception
   * @throws NoSuchPaddingException             the no such padding exception
   * @throws IllegalBlockSizeException          the illegal block size exception
   * @throws BadPaddingException                the bad padding exception
   * @throws InvalidKeyException                the invalid key exception
   * @throws InvalidAlgorithmParameterException the invalid algorithm
   *                                            parameter exception
   * @throws NoSuchProviderException            the no such provider exception
   */
  public byte[] encryptSymmetric(byte[] input, Transformation transformation,
                                 Key key,
                                 AlgorithmParameters algorithmParameters) throws
          NoSuchAlgorithmException, NoSuchPaddingException,
          IllegalBlockSizeException, BadPaddingException, InvalidKeyException
          , InvalidAlgorithmParameterException, NoSuchProviderException {
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
   * @return the byte [ ]
   * @throws NoSuchAlgorithmException           the no such algorithm exception
   * @throws NoSuchPaddingException             the no such padding exception
   * @throws InvalidKeyException                the invalid key exception
   * @throws InvalidAlgorithmParameterException the invalid algorithm
   *                                            parameter exception
   * @throws IllegalBlockSizeException          the illegal block size exception
   * @throws BadPaddingException                the bad padding exception
   * @throws NoSuchProviderException            the no such provider exception
   */
  public byte[] decryptSymmetric(byte[] encryptedBytes,
                                 Transformation transformation, Key key,
                                 AlgorithmParameters algorithmParameters) throws
          NoSuchAlgorithmException, NoSuchPaddingException,
          InvalidKeyException, InvalidAlgorithmParameterException,
          IllegalBlockSizeException, BadPaddingException,
          NoSuchProviderException {
    Cipher cipher = Cipher.getInstance(transformation.toString(),
                                       "BC");
    cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
    return cipher.doFinal(encryptedBytes);
  }
}
