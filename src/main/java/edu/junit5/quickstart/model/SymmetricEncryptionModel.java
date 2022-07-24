package edu.junit5.quickstart.model;

import edu.junit5.quickstart.algorithm.Algorithms;
import edu.junit5.quickstart.state.Transformation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class SymmetricEncryptionModel {
  private OperationResult operationResult;
  private byte[] result;

  private PublicPostEncryptionData publicPostEncryptionData;
  private SecretEncryptionData secretEncryptionData;

  public OperationResult getOperationResult() {
    return operationResult;
  }

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
                        new BouncyCastleProvider());
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
                      new BouncyCastleProvider());
      AlgorithmParameters algorithmParameters =
              algorithmParameterGenerator.generateParameters();

      result = encryptSymmetric(publicPreEncryptionData.getBytesToEncrypt(),
                                publicPreEncryptionData.getTransformation(),
                                key, algorithmParameters);

      this.publicPostEncryptionData = new PublicPostEncryptionData(result,
                                                                   publicPreEncryptionData.getTransformation(),
                                                                   algorithmParameters.getEncoded());
      this.secretEncryptionData = new SecretEncryptionData(key);
    } catch (NoSuchAlgorithmException | IOException e) {

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
              new BouncyCastleProvider());
      algorithmParameters.init(
              publicPostEncryptionData.getAlgorithmParametersAsBytes());
      result = decryptSymmetric(
              publicPostEncryptionData.getEncryptedBytes(),
              publicPostEncryptionData.getTransformation(),
              secretEncryptionData.getKey(),
              algorithmParameters);
      return new OperationResult(true);
    } catch (NoSuchAlgorithmException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public byte[] encryptSymmetric(byte[] input, Transformation transformation,
                                 Key key,
                                 AlgorithmParameters algorithmParameters) {
    try {
      Cipher cipher = Cipher.getInstance(transformation.toString(),
                                         new BouncyCastleProvider());
      cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameters);
      return cipher.doFinal(input);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException |
             IllegalBlockSizeException | BadPaddingException |
             InvalidKeyException | InvalidAlgorithmParameterException e) {
      throw new RuntimeException(e);
    }
  }

  public byte[] decryptSymmetric(byte[] encryptedBytes,
                                 Transformation transformation, Key key,
                                 AlgorithmParameters algorithmParameters) {
    try {

      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
              "PBEWithSHA256And128BitAES-CBC-BC", new BouncyCastleProvider());
      Key testKey = secretKeyFactory.generateSecret(
              new PBEKeySpec(" ".toCharArray(), Hex.decode(
                      "51bc604fcddf79d17734f5232f4d98bcfbe5190445b1cf7263ca427efa4a9c0ec58a717db869da89208cd5f4fe9de4b49952c88ff1ceb0f00747d2b5009838a11278b993a52d80fc6a7b52a34bf205c213466a291de517f0304f52dca70627db2ec77cf492e6d2c97f8707f67d1b039b19bb6cf12ad78b4f00316acc16039ba2a9f3584595304ec7b4eeea9c2c5face7b9a8edd33b89ef4b47f815783d974d92d28906bd8868ea68d3b33e971b749e8bac43a6a9f1f52e22d07936884e82191d38fae7cfdd4903bdd838efc9125cc21545a094ab734fdef016813ccb30469ce1687dfc1f397baceb22dcfd0bc9f1ec4f765ad3f1d9ca78994efc9c8ec44abe93"),
                             10000, 128));
      byte[] keyAsBytescustom = testKey.getEncoded();
      byte[] keyAsBytes = key.getEncoded();

      Cipher cipher = Cipher.getInstance(transformation.toString(),
                                         new BouncyCastleProvider());
      cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
      return cipher.doFinal(encryptedBytes);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException |
             InvalidKeyException | InvalidAlgorithmParameterException |
             IllegalBlockSizeException | BadPaddingException |
             InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }
}
