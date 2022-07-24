package edu.junit5.quickstart.state;

public class Defaults {
  protected static final String symmetricEncryptionAlgorithm = "AES";
  protected static final String symmetricEncryptionMode = "CBC";
  protected static final String symmetricEncryptionPadding = "PKCS7Padding";
  protected static final String symmetricEncryptionKeyLength = "256";

  protected static final String symmetricEncryptionValidation = "HMACSHA256";

  protected static final String passwordGenerationAlgorithm =
          "PBEWithSHA256And128BitAES-CBC-BC";
  protected static final String passwordEncryptionAlgorithm =
          "PBEWithSHA256And128BitAES-CBC-BC";
  protected static final String passwordEncryptionKeyLength = "128";

  protected static final String passwordEncryptionValidation = "HMACSHA256";
}
