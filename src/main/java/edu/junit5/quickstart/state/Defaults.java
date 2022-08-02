package edu.junit5.quickstart.state;

/**
 * Default values for the state.
 *
 * @author Robin Steil
 */
public class Defaults {

  /**
   * The default symmetricEncryptionAlgorithm.
   */
  protected static final String symmetricEncryptionAlgorithm = "AES";
  /**
   * The default symmetricEncryptionMode.
   */
  protected static final String symmetricEncryptionMode = "CBC";
  /**
   * The default symmetricEncryptionPadding.
   */
  protected static final String symmetricEncryptionPadding = "PKCS7Padding";
  /**
   * The default symmetricEncryptionKeyLength.
   */
  protected static final String symmetricEncryptionKeyLength = "256";

  /**
   * The default symmetricEncryptionValidation.
   */
  protected static final String symmetricEncryptionValidation = "HMACSHA256";

  /**
   * The default passwordGenerationAlgorithm.
   */
  protected static final String passwordGenerationAlgorithm =
          "PBEWithSHA256And128BitAES-CBC-BC";
  /**
   * The default passwordEncryptionAlgorithm.
   */
  protected static final String passwordEncryptionAlgorithm =
          "PBEWithSHA256And128BitAES-CBC-BC";
  /**
   * The default passwordEncryptionKeyLength.
   */
  protected static final String passwordEncryptionKeyLength = "128";

  /**
   * The default passwordEncryptionValidation.
   */
  protected static final String passwordEncryptionValidation = "HMACSHA256";

  /**
   * The default signatureSignAlgorithm.
   */
  protected static final String signatureSignAlgorithm = "SHA256withDSA";
}
