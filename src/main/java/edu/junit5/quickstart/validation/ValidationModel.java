package edu.junit5.quickstart.validation;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.security.*;

/**
 * This class handles all functionality in regards generating and verifying
 * validations (digests and MACs).
 */
public class ValidationModel {

  private PublicValidationData publicValidationData;

  private SecretValidationData secretValidationData;

  private void generateValidationWithDigest(
          byte[] bytesToGenerateValidationFor,
          String validationName) throws NoSuchAlgorithmException {
    MessageDigest digest;
    byte[] result;
    digest = MessageDigest.getInstance(validationName,
                                       new BouncyCastleProvider());
    digest.update(bytesToGenerateValidationFor);
    result = digest.digest();
    publicValidationData = new PublicValidationData().fill(
            validationName, result);
    secretValidationData = new SecretValidationData();
  }

  private void generateValidationWithMac(byte[] bytesToGenerateValidationFor,
                                         String validationName,
                                         Key providedKey) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac mac;
    byte[] result;
    Key key = providedKey;

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
  }

  /**
   * Generate validation. No key provided because none is needed for digests.
   *
   * @param bytesToGenerateValidationFor the bytes to generate validation for
   * @param validationName               the validation name
   * @throws GeneralSecurityException the general security exception
   */
  public void generateValidation(
          byte[] bytesToGenerateValidationFor,
          String validationName) throws GeneralSecurityException {
    generateValidation(bytesToGenerateValidationFor, validationName, null);
  }

  /**
   * Generate validation.
   *
   * @param bytesToGenerateValidationFor the bytes to generate validation for
   * @param validationName               the validation name
   * @param key                          the key
   * @throws GeneralSecurityException the general security exception
   */
  public void generateValidation(
          byte[] bytesToGenerateValidationFor, String validationName,
          Key key) throws GeneralSecurityException {

    Validations validations = new Validations();

    if (validations.getValidationType(validationName) ==
            ValidationType.DIGEST) {

      generateValidationWithDigest(
              bytesToGenerateValidationFor, validationName);

    } else if (validations.getValidationType(validationName) ==
            ValidationType.MAC) {

      generateValidationWithMac(
              bytesToGenerateValidationFor, validationName, key);
    }
  }

  private boolean validateWithDigest(byte[] bytesToValidate,
                                     PublicValidationData publicValidationData)
          throws NoSuchAlgorithmException {
    byte[] bytesToValidateAfterComputation;
    MessageDigest messageDigest = MessageDigest.getInstance(
            publicValidationData.getName());
    bytesToValidateAfterComputation = messageDigest.digest(bytesToValidate);
    return Arrays.constantTimeAreEqual(bytesToValidateAfterComputation,
                                       publicValidationData.getComputedBytes());
  }

  private boolean validateWithMac(byte[] bytesToValidate,
                                  PublicValidationData publicValidationData,
                                  SecretValidationData secretValidationData) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac mac = Mac.getInstance(publicValidationData.getName(),
                              new BouncyCastleProvider());
    mac.init(secretValidationData.getKey());
    byte[] result = mac.doFinal(bytesToValidate);
    return Arrays.constantTimeAreEqual(result,
                                       publicValidationData.getComputedBytes());
  }

  /**
   * validates the bytesToValidate with the provided bytes in
   * publicValidationData.
   *
   * @param bytesToValidate      the bytes to validate
   * @param publicValidationData the public validation data
   * @param secretValidationData the secret validation data
   * @return the boolean
   * @throws GeneralSecurityException the general security exception
   */
  public boolean validate(byte[] bytesToValidate,
                          PublicValidationData publicValidationData,
                          SecretValidationData secretValidationData)
          throws GeneralSecurityException {
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

  /**
   * Gets public validation data.
   *
   * @return the public validation data
   */
  public PublicValidationData getPublicValidationData() {
    return publicValidationData;
  }

  /**
   * Gets secret validation data.
   *
   * @return the secret validation data
   */
  public SecretValidationData getSecretValidationData() {
    return secretValidationData;
  }
}
