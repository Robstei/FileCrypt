package edu.junit5.quickstart.model;

import edu.junit5.quickstart.state.Transformation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class SymmetricEncryptionModel {
  private OperationResult operationResult;
  private byte[] result;

  private PublicEncryptionData publicEncryptionData;
  private SecretEncryptionData secretEncryptionData;


  public OperationResult getOperationResult() {
    return operationResult;
  }

  public byte[] getResult() {
    return result;
  }

  public PublicEncryptionData getPublicEncryptionData() {
    return publicEncryptionData;
  }

  public SecretEncryptionData getSecretEncryptionData() {
    return secretEncryptionData;
  }

  public void manageSymmetricEncryption(byte[] bytesToEncrypt,
                                        Transformation transformation,
                                        Key key,
                                        AlgorithmParameters algorithmParameters) {
    result = encryptSymmetric(bytesToEncrypt, transformation,
                              key, algorithmParameters);

    this.publicEncryptionData = new PublicEncryptionData(result, transformation,
                                                         algorithmParameters);
    this.secretEncryptionData = new SecretEncryptionData(key);
  }

  public OperationResult manageSymmetricDecryption(
          PublicEncryptionData publicEncryptionData,
          SecretEncryptionData secretEncryptionData) {

    AlgorithmParameters algorithmParameters =
            publicEncryptionData.getAlgorithmParameters();
    result = decryptSymmetric(publicEncryptionData.getEncryptedBytes(),
                              publicEncryptionData.getTransformation(),
                              secretEncryptionData.getKey(),
                              algorithmParameters);
    return new OperationResult(true);
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
      Cipher cipher = Cipher.getInstance(transformation.toString(),
                                         new BouncyCastleProvider());
      cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
      return cipher.doFinal(encryptedBytes);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException |
             InvalidKeyException |
             InvalidAlgorithmParameterException |
             IllegalBlockSizeException | BadPaddingException e) {
      throw new RuntimeException(e);
    }
  }
}
