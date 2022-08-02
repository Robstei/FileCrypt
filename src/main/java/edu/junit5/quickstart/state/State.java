package edu.junit5.quickstart.state;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The type State.
 *
 * @author Robin Steil
 */
public class State {

  private static final State state = new State();
  private final StringProperty symmetricEncryptionAlgorithm;
  private final StringProperty symmetricEncryptionMode;
  private final StringProperty symmetricEncryptionPadding;
  private final StringProperty symmetricEncryptionKeyLength;
  private final StringProperty symmetricEncryptionEncryptFilePath;
  private final StringProperty symmetricEncryptionDecryptFilePath;
  private final StringProperty symmetricEncryptionKeyFilePath;
  private final StringProperty symmetricEncryptionValidation;
  private final StringProperty passwordForEncryption;
  private final StringProperty passwordGenerationAlgorithm;
  private final StringProperty passwordEncryptionAlgorithm;
  private final StringProperty passwordEncryptionMode;
  private final StringProperty passwordEncryptionPadding;
  private final StringProperty passwordEncryptionKeyLength;
  private final StringProperty passwordEncryptionValidation;
  private final StringProperty passwordEncryptionPath;
  private final StringProperty passwordForDecryption;
  private final StringProperty passwordDecryptionPath;
  private final StringProperty signatureSignAlgorithm;
  private final StringProperty signatureSignFilePath;
  private final StringProperty signatureVerifyFilePath;
  private final StringProperty signatureVerifyKeyFilePath;
  private final StringProperty keyStoreSaveKeyStoreFilePath;
  private final StringProperty keyStoreSaveKeyStorePassword;
  private final StringProperty keyStoreSaveKeyFilePath;
  private final StringProperty keyStoreSaveKeyIdentifier;
  private final StringProperty keyStoreSaveKeyPassword;

  private final StringProperty keyStoreGetKeyStoreFilePath;
  private final StringProperty keyStoreGetKeyStorePassword;
  private final StringProperty keyStoreGetKeyPassword;
  private final StringProperty keyStoreGetKeyIdentifier;


  private State() {
    symmetricEncryptionAlgorithm = new SimpleStringProperty(
            Defaults.symmetricEncryptionAlgorithm);
    symmetricEncryptionMode = new SimpleStringProperty(
            Defaults.symmetricEncryptionMode);
    symmetricEncryptionPadding = new SimpleStringProperty(
            Defaults.symmetricEncryptionPadding);
    symmetricEncryptionKeyLength = new SimpleStringProperty(
            Defaults.symmetricEncryptionKeyLength);
    symmetricEncryptionValidation = new SimpleStringProperty(
            Defaults.symmetricEncryptionValidation);
    passwordGenerationAlgorithm = new SimpleStringProperty(
            Defaults.passwordGenerationAlgorithm);
    passwordEncryptionAlgorithm = new SimpleStringProperty(
            Defaults.passwordEncryptionAlgorithm);
    passwordEncryptionKeyLength = new SimpleStringProperty(
            Defaults.passwordEncryptionKeyLength);
    passwordEncryptionValidation = new SimpleStringProperty(
            Defaults.passwordEncryptionValidation);
    signatureSignAlgorithm = new SimpleStringProperty(
            Defaults.signatureSignAlgorithm);

    symmetricEncryptionEncryptFilePath = new SimpleStringProperty();
    symmetricEncryptionDecryptFilePath = new SimpleStringProperty();
    symmetricEncryptionKeyFilePath = new SimpleStringProperty();
    passwordForEncryption = new SimpleStringProperty();
    passwordEncryptionMode = new SimpleStringProperty("");
    passwordEncryptionPadding = new SimpleStringProperty("");
    passwordEncryptionPath = new SimpleStringProperty();
    passwordForDecryption = new SimpleStringProperty();
    passwordDecryptionPath = new SimpleStringProperty();
    signatureSignFilePath = new SimpleStringProperty();
    signatureVerifyFilePath = new SimpleStringProperty();
    signatureVerifyKeyFilePath = new SimpleStringProperty();
    keyStoreSaveKeyStoreFilePath = new SimpleStringProperty();
    keyStoreSaveKeyStorePassword = new SimpleStringProperty();
    keyStoreSaveKeyFilePath = new SimpleStringProperty();
    keyStoreSaveKeyIdentifier = new SimpleStringProperty();
    keyStoreSaveKeyPassword = new SimpleStringProperty();
    keyStoreGetKeyStoreFilePath = new SimpleStringProperty();
    keyStoreGetKeyStorePassword = new SimpleStringProperty();
    keyStoreGetKeyPassword = new SimpleStringProperty();
    keyStoreGetKeyIdentifier = new SimpleStringProperty();
  }

  /**
   * Gets the only state instance used in FileCrypt.
   *
   * @return the instance
   */
  public static State getInstance() {
    return state;
  }

  /**
   * Gets password generation algorithm.
   *
   * @return the password generation algorithm
   */
  public String getPasswordGenerationAlgorithm() {
    return passwordGenerationAlgorithm.get();
  }

  /**
   * Sets password generation algorithm.
   *
   * @param passwordGenerationAlgorithm the password generation algorithm
   */
  public void setPasswordGenerationAlgorithm(
          String passwordGenerationAlgorithm) {
    this.passwordGenerationAlgorithm.set(passwordGenerationAlgorithm);
  }

  /**
   * Password generation algorithm property string property.
   *
   * @return the string property
   */
  public StringProperty passwordGenerationAlgorithmProperty() {
    return passwordGenerationAlgorithm;
  }

  /**
   * Gets symmetric encryption algorithm.
   *
   * @return the symmetric encryption algorithm
   */
  public String getSymmetricEncryptionAlgorithm() {
    return symmetricEncryptionAlgorithm.get();
  }

  /**
   * Sets symmetric encryption algorithm.
   *
   * @param symmetricEncryptionAlgorithm the symmetric encryption algorithm
   */
  public void setSymmetricEncryptionAlgorithm(
          String symmetricEncryptionAlgorithm) {
    this.symmetricEncryptionAlgorithm.set(symmetricEncryptionAlgorithm);
  }

  /**
   * Symmetric encryption algorithm property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionAlgorithmProperty() {
    return symmetricEncryptionAlgorithm;
  }

  /**
   * Gets symmetric encryption mode.
   *
   * @return the symmetric encryption mode
   */
  public String getSymmetricEncryptionMode() {
    return symmetricEncryptionMode.get();
  }

  /**
   * Sets symmetric encryption mode.
   *
   * @param symmetricEncryptionMode the symmetric encryption mode
   */
  public void setSymmetricEncryptionMode(String symmetricEncryptionMode) {
    this.symmetricEncryptionMode.set(symmetricEncryptionMode);
  }

  /**
   * Symmetric encryption mode property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionModeProperty() {
    return symmetricEncryptionMode;
  }

  /**
   * Gets symmetric encryption padding.
   *
   * @return the symmetric encryption padding
   */
  public String getSymmetricEncryptionPadding() {
    return symmetricEncryptionPadding.get();
  }

  /**
   * Sets symmetric encryption padding.
   *
   * @param symmetricEncryptionPadding the symmetric encryption padding
   */
  public void setSymmetricEncryptionPadding(
          String symmetricEncryptionPadding) {
    this.symmetricEncryptionPadding.set(symmetricEncryptionPadding);
  }

  /**
   * Symmetric encryption padding property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionPaddingProperty() {
    return symmetricEncryptionPadding;
  }

  /**
   * Gets symmetric encryption key length.
   *
   * @return the symmetric encryption key length
   */
  public String getSymmetricEncryptionKeyLength() {
    return symmetricEncryptionKeyLength.get();
  }

  /**
   * Sets symmetric encryption key length.
   *
   * @param symmetricEncryptionKeyLength the symmetric encryption key length
   */
  public void setSymmetricEncryptionKeyLength(
          String symmetricEncryptionKeyLength) {
    this.symmetricEncryptionKeyLength.set(symmetricEncryptionKeyLength);
  }

  /**
   * Symmetric encryption key length property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionKeyLengthProperty() {
    return symmetricEncryptionKeyLength;
  }

  /**
   * Gets symmetric encryption encrypt file path.
   *
   * @return the symmetric encryption encrypt file path
   */
  public String getSymmetricEncryptionEncryptFilePath() {
    return symmetricEncryptionEncryptFilePath.get();
  }

  /**
   * Sets symmetric encryption encrypt file path.
   *
   * @param filePath the file path
   */
  public void setSymmetricEncryptionEncryptFilePath(String filePath) {
    this.symmetricEncryptionEncryptFilePath.set(filePath);
  }

  /**
   * File path property string property.
   *
   * @return the string property
   */
  public StringProperty filePathProperty() {
    return symmetricEncryptionEncryptFilePath;
  }

  /**
   * Gets symmetric encryption decrypt file path.
   *
   * @return the symmetric encryption decrypt file path
   */
  public String getSymmetricEncryptionDecryptFilePath() {
    return symmetricEncryptionDecryptFilePath.get();
  }

  /**
   * Sets symmetric encryption decrypt file path.
   *
   * @param symmetricEncryptionDecryptFilePath the symmetric encryption
   *                                           decrypt file path
   */
  public void setSymmetricEncryptionDecryptFilePath(
          String symmetricEncryptionDecryptFilePath) {
    this.symmetricEncryptionDecryptFilePath.set(
            symmetricEncryptionDecryptFilePath);
  }

  /**
   * Symmetric encryption decrypt file path property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionDecryptFilePathProperty() {
    return symmetricEncryptionDecryptFilePath;
  }

  /**
   * Gets symmetric encryption key file path.
   *
   * @return the symmetric encryption key file path
   */
  public String getSymmetricEncryptionKeyFilePath() {
    return symmetricEncryptionKeyFilePath.get();
  }

  /**
   * Sets symmetric encryption key file path.
   *
   * @param symmetricEncryptionKeyFilePath the symmetric encryption key file
   *                                       path
   */
  public void setSymmetricEncryptionKeyFilePath(
          String symmetricEncryptionKeyFilePath) {
    this.symmetricEncryptionKeyFilePath.set(symmetricEncryptionKeyFilePath);
  }

  /**
   * Symmetric encryption key file path property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionKeyFilePathProperty() {
    return symmetricEncryptionKeyFilePath;
  }

  /**
   * Gets symmetric encryption validation.
   *
   * @return the symmetric encryption validation
   */
  public String getSymmetricEncryptionValidation() {
    return symmetricEncryptionValidation.get();
  }

  /**
   * Sets symmetric encryption validation.
   *
   * @param symmetricEncryptionValidation the symmetric encryption validation
   */
  public void setSymmetricEncryptionValidation(
          String symmetricEncryptionValidation) {
    this.symmetricEncryptionValidation.set(symmetricEncryptionValidation);
  }

  /**
   * Symmetric encryption validation property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionValidationProperty() {
    return symmetricEncryptionValidation;
  }

  /**
   * Gets password encryption algorithm.
   *
   * @return the password encryption algorithm
   */
  public String getPasswordEncryptionAlgorithm() {
    return passwordEncryptionAlgorithm.get();
  }

  /**
   * Sets password encryption algorithm.
   *
   * @param passwordEncryptionAlgorithm the password encryption algorithm
   */
  public void setPasswordEncryptionAlgorithm(
          String passwordEncryptionAlgorithm) {
    this.passwordEncryptionAlgorithm.set(
            passwordEncryptionAlgorithm);
  }

  /**
   * Password encryption algorithm property string property.
   *
   * @return the string property
   */
  public StringProperty passwordEncryptionAlgorithmProperty() {
    return passwordEncryptionAlgorithm;
  }

  /**
   * Gets password for encryption.
   *
   * @return the password for encryption
   */
  public String getPasswordForEncryption() {
    return passwordForEncryption.get();
  }

  /**
   * Sets password for encryption.
   *
   * @param passwordForEncryption the password for encryption
   */
  public void setPasswordForEncryption(String passwordForEncryption) {
    this.passwordForEncryption.set(passwordForEncryption);
  }

  /**
   * Password for encryption property string property.
   *
   * @return the string property
   */
  public StringProperty passwordForEncryptionProperty() {
    return passwordForEncryption;
  }

  /**
   * Gets password encryption path.
   *
   * @return the password encryption path
   */
  public String getPasswordEncryptionPath() {
    return passwordEncryptionPath.get();
  }

  /**
   * Sets password encryption path.
   *
   * @param passwordEncryptionPath the password encryption path
   */
  public void setPasswordEncryptionPath(String passwordEncryptionPath) {
    this.passwordEncryptionPath.set(passwordEncryptionPath);
  }

  /**
   * Password encryption path property string property.
   *
   * @return the string property
   */
  public StringProperty passwordEncryptionPathProperty() {
    return passwordEncryptionPath;
  }

  /**
   * Symmetric encryption encrypt file path property string property.
   *
   * @return the string property
   */
  public StringProperty symmetricEncryptionEncryptFilePathProperty() {
    return symmetricEncryptionEncryptFilePath;
  }

  /**
   * Gets password encryption mode.
   *
   * @return the password encryption mode
   */
  public String getPasswordEncryptionMode() {
    return passwordEncryptionMode.get();
  }

  /**
   * Sets password encryption mode.
   *
   * @param passwordEncryptionMode the password encryption mode
   */
  public void setPasswordEncryptionMode(String passwordEncryptionMode) {
    this.passwordEncryptionMode.set(passwordEncryptionMode);
  }

  /**
   * Password encryption mode property string property.
   *
   * @return the string property
   */
  public StringProperty passwordEncryptionModeProperty() {
    return passwordEncryptionMode;
  }

  /**
   * Gets password encryption padding.
   *
   * @return the password encryption padding
   */
  public String getPasswordEncryptionPadding() {
    return passwordEncryptionPadding.get();
  }

  /**
   * Sets password encryption padding.
   *
   * @param passwordEncryptionPadding the password encryption padding
   */
  public void setPasswordEncryptionPadding(
          String passwordEncryptionPadding) {
    this.passwordEncryptionPadding.set(passwordEncryptionPadding);
  }

  /**
   * Password encryption padding property string property.
   *
   * @return the string property
   */
  public StringProperty passwordEncryptionPaddingProperty() {
    return passwordEncryptionPadding;
  }

  /**
   * Gets password for decryption.
   *
   * @return the password for decryption
   */
  public String getPasswordForDecryption() {
    return passwordForDecryption.get();
  }

  /**
   * Sets password for decryption.
   *
   * @param passwordForDecryption the password for decryption
   */
  public void setPasswordForDecryption(String passwordForDecryption) {
    this.passwordForDecryption.set(passwordForDecryption);
  }

  /**
   * Password for decryption property string property.
   *
   * @return the string property
   */
  public StringProperty passwordForDecryptionProperty() {
    return passwordForDecryption;
  }

  /**
   * Gets password decryption path.
   *
   * @return the password decryption path
   */
  public String getPasswordDecryptionPath() {
    return passwordDecryptionPath.get();
  }

  /**
   * Sets password decryption path.
   *
   * @param passwordDecryptionPath the password decryption path
   */
  public void setPasswordDecryptionPath(String passwordDecryptionPath) {
    this.passwordDecryptionPath.set(passwordDecryptionPath);
  }

  /**
   * Password decryption path property string property.
   *
   * @return the string property
   */
  public StringProperty passwordDecryptionPathProperty() {
    return passwordDecryptionPath;
  }

  /**
   * Gets password encryption key length.
   *
   * @return the password encryption key length
   */
  public String getPasswordEncryptionKeyLength() {
    return passwordEncryptionKeyLength.get();
  }

  /**
   * Sets password encryption key length.
   *
   * @param passwordEncryptionKeyLength the password encryption key length
   */
  public void setPasswordEncryptionKeyLength(
          String passwordEncryptionKeyLength) {
    this.passwordEncryptionKeyLength.set(passwordEncryptionKeyLength);
  }

  /**
   * Password encryption key length property string property.
   *
   * @return the string property
   */
  public StringProperty passwordEncryptionKeyLengthProperty() {
    return passwordEncryptionKeyLength;
  }

  /**
   * Gets password encryption validation.
   *
   * @return the password encryption validation
   */
  public String getPasswordEncryptionValidation() {
    return passwordEncryptionValidation.get();
  }

  /**
   * Sets password encryption validation.
   *
   * @param passwordEncryptionValidation the password encryption validation
   */
  public void setPasswordEncryptionValidation(
          String passwordEncryptionValidation) {
    this.passwordEncryptionValidation.set(passwordEncryptionValidation);
  }

  /**
   * Password encryption validation property string property.
   *
   * @return the string property
   */
  public StringProperty passwordEncryptionValidationProperty() {
    return passwordEncryptionValidation;
  }

  /**
   * Gets signature sign algorithm.
   *
   * @return the signature sign algorithm
   */
  public String getSignatureSignAlgorithm() {
    return signatureSignAlgorithm.get();
  }

  /**
   * Sets signature sign algorithm.
   *
   * @param signatureSignAlgorithm the signature sign algorithm
   */
  public void setSignatureSignAlgorithm(String signatureSignAlgorithm) {
    this.signatureSignAlgorithm.set(signatureSignAlgorithm);
  }

  /**
   * Signature sign algorithm property string property.
   *
   * @return the string property
   */
  public StringProperty signatureSignAlgorithmProperty() {
    return signatureSignAlgorithm;
  }

  /**
   * Gets signature sign file path.
   *
   * @return the signature sign file path
   */
  public String getSignatureSignFilePath() {
    return signatureSignFilePath.get();
  }

  /**
   * Sets signature sign file path.
   *
   * @param signatureSignFilePath the signature sign file path
   */
  public void setSignatureSignFilePath(String signatureSignFilePath) {
    this.signatureSignFilePath.set(signatureSignFilePath);
  }

  /**
   * Signature sign file path property string property.
   *
   * @return the string property
   */
  public StringProperty signatureSignFilePathProperty() {
    return signatureSignFilePath;
  }

  /**
   * Gets signature verify file path.
   *
   * @return the signature verify file path
   */
  public String getSignatureVerifyFilePath() {
    return signatureVerifyFilePath.get();
  }

  /**
   * Sets signature verify file path.
   *
   * @param signatureVerifyFilePath the signature verify file path
   */
  public void setSignatureVerifyFilePath(String signatureVerifyFilePath) {
    this.signatureVerifyFilePath.set(signatureVerifyFilePath);
  }

  /**
   * Signature verify file path property string property.
   *
   * @return the string property
   */
  public StringProperty signatureVerifyFilePathProperty() {
    return signatureVerifyFilePath;
  }

  /**
   * Gets signature verify key file path.
   *
   * @return the signature verify key file path
   */
  public String getSignatureVerifyKeyFilePath() {
    return signatureVerifyKeyFilePath.get();
  }

  /**
   * Sets signature verify key file path.
   *
   * @param signatureVerifyKeyFilePath the signature verify key file path
   */
  public void setSignatureVerifyKeyFilePath(String signatureVerifyKeyFilePath) {
    this.signatureVerifyKeyFilePath.set(signatureVerifyKeyFilePath);
  }

  /**
   * Signature verify key file path property string property.
   *
   * @return the string property
   */
  public StringProperty signatureVerifyKeyFilePathProperty() {
    return signatureVerifyKeyFilePath;
  }

  /**
   * Gets key store save key store file path.
   *
   * @return the key store save key store file path
   */
  public String getKeyStoreSaveKeyStoreFilePath() {
    return keyStoreSaveKeyStoreFilePath.get();
  }

  /**
   * Sets key store save key store file path.
   *
   * @param keyStoreSaveKeyStoreFilePath the key store save key store file path
   */
  public void setKeyStoreSaveKeyStoreFilePath(
          String keyStoreSaveKeyStoreFilePath) {
    this.keyStoreSaveKeyStoreFilePath.set(keyStoreSaveKeyStoreFilePath);
  }

  /**
   * Key store save key store file path property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreSaveKeyStoreFilePathProperty() {
    return keyStoreSaveKeyStoreFilePath;
  }

  /**
   * Gets key store save key store password.
   *
   * @return the key store save key store password
   */
  public String getKeyStoreSaveKeyStorePassword() {
    return keyStoreSaveKeyStorePassword.get();
  }

  /**
   * Sets key store save key store password.
   *
   * @param keyStoreSaveKeyStorePassword the key store save key store password
   */
  public void setKeyStoreSaveKeyStorePassword(
          String keyStoreSaveKeyStorePassword) {
    this.keyStoreSaveKeyStorePassword.set(keyStoreSaveKeyStorePassword);
  }

  /**
   * Key store save key store password property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreSaveKeyStorePasswordProperty() {
    return keyStoreSaveKeyStorePassword;
  }

  /**
   * Gets key store save key file path.
   *
   * @return the key store save key file path
   */
  public String getKeyStoreSaveKeyFilePath() {
    return keyStoreSaveKeyFilePath.get();
  }

  /**
   * Sets key store save key file path.
   *
   * @param keyStoreSaveKeyFilePath the key store save key file path
   */
  public void setKeyStoreSaveKeyFilePath(String keyStoreSaveKeyFilePath) {
    this.keyStoreSaveKeyFilePath.set(keyStoreSaveKeyFilePath);
  }

  /**
   * Key store save key file path property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreSaveKeyFilePathProperty() {
    return keyStoreSaveKeyFilePath;
  }

  /**
   * Gets key store save key identifier.
   *
   * @return the key store save key identifier
   */
  public String getKeyStoreSaveKeyIdentifier() {
    return keyStoreSaveKeyIdentifier.get();
  }

  /**
   * Sets key store save key identifier.
   *
   * @param keyStoreSaveKeyIdentifier the key store save key identifier
   */
  public void setKeyStoreSaveKeyIdentifier(
          String keyStoreSaveKeyIdentifier) {
    this.keyStoreSaveKeyIdentifier.set(keyStoreSaveKeyIdentifier);
  }

  /**
   * Key store save key identifier property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreSaveKeyIdentifierProperty() {
    return keyStoreSaveKeyIdentifier;
  }

  /**
   * Gets key store save key password.
   *
   * @return the key store save key password
   */
  public String getKeyStoreSaveKeyPassword() {
    return keyStoreSaveKeyPassword.get();
  }

  /**
   * Sets key store save key password.
   *
   * @param keyStoreSaveKeyPassword the key store save key password
   */
  public void setKeyStoreSaveKeyPassword(String keyStoreSaveKeyPassword) {
    this.keyStoreSaveKeyPassword.set(keyStoreSaveKeyPassword);
  }

  /**
   * Key store save key password property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreSaveKeyPasswordProperty() {
    return keyStoreSaveKeyPassword;
  }

  /**
   * Gets key store get key store file path.
   *
   * @return the key store get key store file path
   */
  public String getKeyStoreGetKeyStoreFilePath() {
    return keyStoreGetKeyStoreFilePath.get();
  }

  /**
   * Sets key store get key store file path.
   *
   * @param keyStoreGetKeyStoreFilePath the key store get key store file path
   */
  public void setKeyStoreGetKeyStoreFilePath(
          String keyStoreGetKeyStoreFilePath) {
    this.keyStoreGetKeyStoreFilePath.set(keyStoreGetKeyStoreFilePath);
  }

  /**
   * Key store get key store file path property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreGetKeyStoreFilePathProperty() {
    return keyStoreGetKeyStoreFilePath;
  }

  /**
   * Gets key store get key password.
   *
   * @return the key store get key password
   */
  public String getKeyStoreGetKeyPassword() {
    return keyStoreGetKeyPassword.get();
  }

  /**
   * Sets key store get key password.
   *
   * @param keyStoreGetKeyPassword the key store get key password
   */
  public void setKeyStoreGetKeyPassword(String keyStoreGetKeyPassword) {
    this.keyStoreGetKeyPassword.set(keyStoreGetKeyPassword);
  }

  /**
   * Key store get key password property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreGetKeyPasswordProperty() {
    return keyStoreGetKeyPassword;
  }

  /**
   * Gets key store get key identifier.
   *
   * @return the key store get key identifier
   */
  public String getKeyStoreGetKeyIdentifier() {
    return keyStoreGetKeyIdentifier.get();
  }

  /**
   * Sets key store get key identifier.
   *
   * @param keyStoreGetKeyIdentifier the key store get key identifier
   */
  public void setKeyStoreGetKeyIdentifier(String keyStoreGetKeyIdentifier) {
    this.keyStoreGetKeyIdentifier.set(keyStoreGetKeyIdentifier);
  }

  /**
   * Key store get key identifier property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreGetKeyIdentifierProperty() {
    return keyStoreGetKeyIdentifier;
  }

  /**
   * Gets key store get key store password.
   *
   * @return the key store get key store password
   */
  public String getKeyStoreGetKeyStorePassword() {
    return keyStoreGetKeyStorePassword.get();
  }

  /**
   * Sets key store get key store password.
   *
   * @param keyStoreGetKeyStorePassword the key store get key store password
   */
  public void setKeyStoreGetKeyStorePassword(
          String keyStoreGetKeyStorePassword) {
    this.keyStoreGetKeyStorePassword.set(keyStoreGetKeyStorePassword);
  }

  /**
   * Key store get key store password property string property.
   *
   * @return the string property
   */
  public StringProperty keyStoreGetKeyStorePasswordProperty() {
    return keyStoreGetKeyStorePassword;
  }
}
