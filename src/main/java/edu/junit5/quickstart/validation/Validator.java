package edu.junit5.quickstart.validation;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Validator {

  private PublicValidationData publicValidationData;

  private SecretValidationData secretValidationData;

  private void generateValidationWithDigest(
          byte[] bytesToGenerateValidationFor, String validationName) {
    MessageDigest digest;
    byte[] result;
    try {
      digest = MessageDigest.getInstance(validationName,
                                         new BouncyCastleProvider());
      digest.update(bytesToGenerateValidationFor);
      result = digest.digest();
      publicValidationData = new PublicValidationData().fill(
              validationName, result);
      secretValidationData = null;
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  private void generateValidationWithMac(byte[] bytesToGenerateValidationFor,
                                         String validationName,
                                         Key providedKey) {
    Mac mac;
    byte[] result;
    Key key = providedKey;
    try {

      if (key == null) {
        Validations validations = new Validations();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(
                validations.getKeyInitializer(validationName));
        key = keyGenerator.generateKey();
      }

      mac = Mac.getInstance(validationName, new BouncyCastleProvider());
      mac.init(key);
      result = mac.doFinal(bytesToGenerateValidationFor);
      publicValidationData = new PublicValidationData().fill(
              validationName, result);
      secretValidationData = new SecretValidationData().fill(key);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new RuntimeException(e);
    }
  }

  public void generateValidation(
          byte[] bytesToGenerateValidationFor, String validationName) {
    generateValidation(bytesToGenerateValidationFor, validationName, null);
  }

  public void generateValidation(
          byte[] bytesToGenerateValidationFor, String validationName, Key key) {
    Validations validations = new Validations();
    if (validations.getValidationType(
            validationName) == ValidationType.DIGEST) {
      generateValidationWithDigest(
              bytesToGenerateValidationFor, validationName);
    } else if (validations.getValidationType(
            validationName) == ValidationType.MAC) {
      generateValidationWithMac(
              bytesToGenerateValidationFor, validationName, key);
    }
  }

  private boolean validateWithDigest(byte[] bytesToValidate,
                                     PublicValidationData publicValidationData) {
    byte[] bytesToValidateAfterComputation;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance(
              publicValidationData.getName());
      bytesToValidateAfterComputation = messageDigest.digest(bytesToValidate);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    return Arrays.constantTimeAreEqual(bytesToValidateAfterComputation,
                                       publicValidationData.getComputedBytes());
  }

  private boolean validateWithMac(byte[] bytesToValidate,
                                  PublicValidationData publicValidationData,
                                  SecretValidationData secretValidationData) {
    try {
      Mac mac = Mac.getInstance(publicValidationData.getName(),
                                new BouncyCastleProvider());
      mac.init(secretValidationData.getKey());
      byte[] result = mac.doFinal(bytesToValidate);
      return Arrays.constantTimeAreEqual(result,
                                         publicValidationData.getComputedBytes());
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean validate(byte[] bytesToValidate,
                          PublicValidationData publicValidationData,
                          SecretValidationData secretValidationData) {
    boolean valid = false;
    Validations validations = new Validations();
    if (validations.getValidationType(
            publicValidationData.getName()) == ValidationType.DIGEST) {
      valid = validateWithDigest(bytesToValidate,
                                 publicValidationData);
    } else if (validations.getValidationType(
            publicValidationData.getName()) == ValidationType.MAC) {
      valid = validateWithMac(bytesToValidate, publicValidationData,
                              secretValidationData);
    }
    return valid;
  }

  public PublicValidationData getPublicValidationData() {
    return publicValidationData;
  }

  public SecretValidationData getSecretValidationData() {
    return secretValidationData;
  }
}
