package edu.junit5.quickstart.model;

import edu.junit5.quickstart.algorithm.Algorithms;
import edu.junit5.quickstart.state.Transformation;

import javax.crypto.*;
import java.io.IOException;
import java.security.*;

public class SymmetricEncryptionModel {
  private byte[] result;
  private PublicPostEncryptionData publicPostEncryptionData;
  private SecretEncryptionData secretEncryptionData;

  public byte[] getResult() {
    return result;
  }

  public PublicPostEncryptionData getPublicEncryptionData() {
    return publicPostEncryptionData;
  }

  public SecretEncryptionData getSecretEncryptionData() {
    return secretEncryptionData;
  }

  public void manageSymmetricEncryption(
          PublicPreEncryptionData publicPreEncryptionData) {
    manageSymmetricEncryption(publicPreEncryptionData, null);
  }

  public void manageSymmetricEncryption(
          PublicPreEncryptionData publicPreEncryptionData, Key providedKey) {
    try {
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

      // generate parameter
      Algorithms algorithms = new Algorithms();
      String algorithmForParameterGeneration =
              algorithms.getNameForParameterGeneration(
                      publicPreEncryptionData.getAlgorithm());
      AlgorithmParameterGenerator algorithmParameterGenerator =
              AlgorithmParameterGenerator.getInstance(
                      algorithmForParameterGeneration,
                      "BC");
      AlgorithmParameters algorithmParameters =
              algorithmParameterGenerator.generateParameters();

      result = encryptSymmetric(publicPreEncryptionData.getBytesToEncrypt(),
                                publicPreEncryptionData.getTransformation(),
                                key, algorithmParameters);

      this.publicPostEncryptionData = new PublicPostEncryptionData().fill(
              result,
              publicPreEncryptionData.getTransformation(),
              algorithmParameters.getEncoded());
      this.secretEncryptionData = new SecretEncryptionData().fill(key);
    } catch (NoSuchAlgorithmException | IOException |
             NoSuchProviderException e) {
      throw new RuntimeException(e);
    }
  }

  public OperationResult manageSymmetricDecryption(
          PublicPostEncryptionData publicPostEncryptionData,
          SecretEncryptionData secretEncryptionData) {
    try {
      Algorithms algorithms = new Algorithms();
      String nameForParameterGeneration =
              algorithms.getNameForParameterGeneration(
                      publicPostEncryptionData.getAlgorithm());
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
      return new OperationResult(true);
    } catch (NoSuchAlgorithmException | IOException |
             NoSuchProviderException e) {
      throw new RuntimeException(e);
    }
  }

  public byte[] encryptSymmetric(byte[] input, Transformation transformation,
                                 Key key,
                                 AlgorithmParameters algorithmParameters) {
    try {
      Cipher cipher = Cipher.getInstance(transformation.toString(),
                                         "BC");
      cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameters);
      return cipher.doFinal(input);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException |
             IllegalBlockSizeException | BadPaddingException |
             InvalidKeyException | InvalidAlgorithmParameterException |
             NoSuchProviderException e) {
      throw new RuntimeException(e);
    }
  }

  public byte[] decryptSymmetric(byte[] encryptedBytes,
                                 Transformation transformation, Key key,
                                 AlgorithmParameters algorithmParameters) {
    try {
      Cipher cipher = Cipher.getInstance(transformation.toString(),
                                         "BC");
      cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
      return cipher.doFinal(encryptedBytes);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException |
             InvalidKeyException | InvalidAlgorithmParameterException |
             IllegalBlockSizeException | BadPaddingException |
             NoSuchProviderException e) {
      throw new RuntimeException(e);
    }
  }
}
