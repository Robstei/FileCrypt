package edu.junit5.quickstart.state;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class State {

  private static State state = new State();
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

  public static State getInstance() {
    return state;
  }

  public String getPasswordGenerationAlgorithm() {
    return passwordGenerationAlgorithm.get();
  }

  public void setPasswordGenerationAlgorithm(
          String passwordGenerationAlgorithm) {
    this.passwordGenerationAlgorithm.set(passwordGenerationAlgorithm);
  }

  public StringProperty passwordGenerationAlgorithmProperty() {
    return passwordGenerationAlgorithm;
  }

  public String getSymmetricEncryptionAlgorithm() {
    return symmetricEncryptionAlgorithm.get();
  }

  public void setSymmetricEncryptionAlgorithm(
          String symmetricEncryptionAlgorithm) {
    this.symmetricEncryptionAlgorithm.set(symmetricEncryptionAlgorithm);
  }

  public StringProperty symmetricEncryptionAlgorithmProperty() {
    return symmetricEncryptionAlgorithm;
  }

  public String getSymmetricEncryptionMode() {
    return symmetricEncryptionMode.get();
  }

  public void setSymmetricEncryptionMode(String symmetricEncryptionMode) {
    this.symmetricEncryptionMode.set(symmetricEncryptionMode);
  }

  public StringProperty symmetricEncryptionModeProperty() {
    return symmetricEncryptionMode;
  }

  public String getSymmetricEncryptionPadding() {
    return symmetricEncryptionPadding.get();
  }

  public void setSymmetricEncryptionPadding(
          String symmetricEncryptionPadding) {
    this.symmetricEncryptionPadding.set(symmetricEncryptionPadding);
  }

  public StringProperty symmetricEncryptionPaddingProperty() {
    return symmetricEncryptionPadding;
  }

  public String getSymmetricEncryptionKeyLength() {
    return symmetricEncryptionKeyLength.get();
  }

  public void setSymmetricEncryptionKeyLength(
          String symmetricEncryptionKeyLength) {
    this.symmetricEncryptionKeyLength.set(symmetricEncryptionKeyLength);
  }

  public StringProperty symmetricEncryptionKeyLengthProperty() {
    return symmetricEncryptionKeyLength;
  }

  public String getSymmetricEncryptionEncryptFilePath() {
    return symmetricEncryptionEncryptFilePath.get();
  }

  public void setSymmetricEncryptionEncryptFilePath(String filePath) {
    this.symmetricEncryptionEncryptFilePath.set(filePath);
  }

  public StringProperty filePathProperty() {
    return symmetricEncryptionEncryptFilePath;
  }

  public String getSymmetricEncryptionDecryptFilePath() {
    return symmetricEncryptionDecryptFilePath.get();
  }

  public void setSymmetricEncryptionDecryptFilePath(
          String symmetricEncryptionDecryptFilePath) {
    this.symmetricEncryptionDecryptFilePath.set(
            symmetricEncryptionDecryptFilePath);
  }

  public StringProperty symmetricEncryptionDecryptFilePathProperty() {
    return symmetricEncryptionDecryptFilePath;
  }

  public String getSymmetricEncryptionKeyFilePath() {
    return symmetricEncryptionKeyFilePath.get();
  }

  public void setSymmetricEncryptionKeyFilePath(
          String symmetricEncryptionKeyFilePath) {
    this.symmetricEncryptionKeyFilePath.set(symmetricEncryptionKeyFilePath);
  }

  public StringProperty symmetricEncryptionKeyFilePathProperty() {
    return symmetricEncryptionKeyFilePath;
  }

  public String getSymmetricEncryptionValidation() {
    return symmetricEncryptionValidation.get();
  }

  public void setSymmetricEncryptionValidation(
          String symmetricEncryptionValidation) {
    this.symmetricEncryptionValidation.set(symmetricEncryptionValidation);
  }

  public StringProperty symmetricEncryptionValidationProperty() {
    return symmetricEncryptionValidation;
  }

  public String getPasswordEncryptionAlgorithm() {
    return passwordEncryptionAlgorithm.get();
  }

  public void setPasswordEncryptionAlgorithm(
          String passwordEncryptionAlgorithm) {
    this.passwordEncryptionAlgorithm.set(
            passwordEncryptionAlgorithm);
  }

  public StringProperty passwordEncryptionAlgorithmProperty() {
    return passwordEncryptionAlgorithm;
  }

  public String getPasswordForEncryption() {
    return passwordForEncryption.get();
  }

  public void setPasswordForEncryption(String passwordForEncryption) {
    this.passwordForEncryption.set(passwordForEncryption);
  }

  public StringProperty passwordForEncryptionProperty() {
    return passwordForEncryption;
  }

  public String getPasswordEncryptionPath() {
    return passwordEncryptionPath.get();
  }

  public void setPasswordEncryptionPath(String passwordEncryptionPath) {
    this.passwordEncryptionPath.set(passwordEncryptionPath);
  }

  public StringProperty passwordEncryptionPathProperty() {
    return passwordEncryptionPath;
  }

  public StringProperty symmetricEncryptionEncryptFilePathProperty() {
    return symmetricEncryptionEncryptFilePath;
  }

  public String getPasswordEncryptionMode() {
    return passwordEncryptionMode.get();
  }

  public void setPasswordEncryptionMode(String passwordEncryptionMode) {
    this.passwordEncryptionMode.set(passwordEncryptionMode);
  }

  public StringProperty passwordEncryptionModeProperty() {
    return passwordEncryptionMode;
  }

  public String getPasswordEncryptionPadding() {
    return passwordEncryptionPadding.get();
  }

  public void setPasswordEncryptionPadding(
          String passwordEncryptionPadding) {
    this.passwordEncryptionPadding.set(passwordEncryptionPadding);
  }

  public StringProperty passwordEncryptionPaddingProperty() {
    return passwordEncryptionPadding;
  }

  public String getPasswordForDecryption() {
    return passwordForDecryption.get();
  }

  public void setPasswordForDecryption(String passwordForDecryption) {
    this.passwordForDecryption.set(passwordForDecryption);
  }

  public StringProperty passwordForDecryptionProperty() {
    return passwordForDecryption;
  }

  public String getPasswordDecryptionPath() {
    return passwordDecryptionPath.get();
  }

  public void setPasswordDecryptionPath(String passwordDecryptionPath) {
    this.passwordDecryptionPath.set(passwordDecryptionPath);
  }

  public StringProperty passwordDecryptionPathProperty() {
    return passwordDecryptionPath;
  }

  public String getPasswordEncryptionKeyLength() {
    return passwordEncryptionKeyLength.get();
  }

  public void setPasswordEncryptionKeyLength(
          String passwordEncryptionKeyLength) {
    this.passwordEncryptionKeyLength.set(passwordEncryptionKeyLength);
  }

  public StringProperty passwordEncryptionKeyLengthProperty() {
    return passwordEncryptionKeyLength;
  }

  public String getPasswordEncryptionValidation() {
    return passwordEncryptionValidation.get();
  }

  public void setPasswordEncryptionValidation(
          String passwordEncryptionValidation) {
    this.passwordEncryptionValidation.set(passwordEncryptionValidation);
  }

  public StringProperty passwordEncryptionValidationProperty() {
    return passwordEncryptionValidation;
  }

  public String getSignatureSignAlgorithm() {
    return signatureSignAlgorithm.get();
  }

  public void setSignatureSignAlgorithm(String signatureSignAlgorithm) {
    this.signatureSignAlgorithm.set(signatureSignAlgorithm);
  }

  public StringProperty signatureSignAlgorithmProperty() {
    return signatureSignAlgorithm;
  }

  public String getSignatureSignFilePath() {
    return signatureSignFilePath.get();
  }

  public void setSignatureSignFilePath(String signatureSignFilePath) {
    this.signatureSignFilePath.set(signatureSignFilePath);
  }

  public StringProperty signatureSignFilePathProperty() {
    return signatureSignFilePath;
  }

  public String getSignatureVerifyFilePath() {
    return signatureVerifyFilePath.get();
  }

  public void setSignatureVerifyFilePath(String signatureVerifyFilePath) {
    this.signatureVerifyFilePath.set(signatureVerifyFilePath);
  }

  public StringProperty signatureVerifyFilePathProperty() {
    return signatureVerifyFilePath;
  }

  public String getSignatureVerifyKeyFilePath() {
    return signatureVerifyKeyFilePath.get();
  }

  public void setSignatureVerifyKeyFilePath(String signatureVerifyKeyFilePath) {
    this.signatureVerifyKeyFilePath.set(signatureVerifyKeyFilePath);
  }

  public StringProperty signatureVerifyKeyFilePathProperty() {
    return signatureVerifyKeyFilePath;
  }

  public String getKeyStoreSaveKeyStoreFilePath() {
    return keyStoreSaveKeyStoreFilePath.get();
  }

  public void setKeyStoreSaveKeyStoreFilePath(
          String keyStoreSaveKeyStoreFilePath) {
    this.keyStoreSaveKeyStoreFilePath.set(keyStoreSaveKeyStoreFilePath);
  }

  public StringProperty keyStoreSaveKeyStoreFilePathProperty() {
    return keyStoreSaveKeyStoreFilePath;
  }

  public String getKeyStoreSaveKeyStorePassword() {
    return keyStoreSaveKeyStorePassword.get();
  }

  public void setKeyStoreSaveKeyStorePassword(
          String keyStoreSaveKeyStorePassword) {
    this.keyStoreSaveKeyStorePassword.set(keyStoreSaveKeyStorePassword);
  }

  public StringProperty keyStoreSaveKeyStorePasswordProperty() {
    return keyStoreSaveKeyStorePassword;
  }

  public String getKeyStoreSaveKeyFilePath() {
    return keyStoreSaveKeyFilePath.get();
  }

  public void setKeyStoreSaveKeyFilePath(String keyStoreSaveKeyFilePath) {
    this.keyStoreSaveKeyFilePath.set(keyStoreSaveKeyFilePath);
  }

  public StringProperty keyStoreSaveKeyFilePathProperty() {
    return keyStoreSaveKeyFilePath;
  }

  public String getKeyStoreSaveKeyIdentifier() {
    return keyStoreSaveKeyIdentifier.get();
  }

  public void setKeyStoreSaveKeyIdentifier(
          String keyStoreSaveKeyIdentifier) {
    this.keyStoreSaveKeyIdentifier.set(keyStoreSaveKeyIdentifier);
  }

  public StringProperty keyStoreSaveKeyIdentifierProperty() {
    return keyStoreSaveKeyIdentifier;
  }

  public String getKeyStoreSaveKeyPassword() {
    return keyStoreSaveKeyPassword.get();
  }

  public void setKeyStoreSaveKeyPassword(String keyStoreSaveKeyPassword) {
    this.keyStoreSaveKeyPassword.set(keyStoreSaveKeyPassword);
  }

  public StringProperty keyStoreSaveKeyPasswordProperty() {
    return keyStoreSaveKeyPassword;
  }

  public String getKeyStoreGetKeyStoreFilePath() {
    return keyStoreGetKeyStoreFilePath.get();
  }

  public void setKeyStoreGetKeyStoreFilePath(
          String keyStoreGetKeyStoreFilePath) {
    this.keyStoreGetKeyStoreFilePath.set(keyStoreGetKeyStoreFilePath);
  }

  public StringProperty keyStoreGetKeyStoreFilePathProperty() {
    return keyStoreGetKeyStoreFilePath;
  }

  public String getKeyStoreGetKeyPassword() {
    return keyStoreGetKeyPassword.get();
  }

  public void setKeyStoreGetKeyPassword(String keyStoreGetKeyPassword) {
    this.keyStoreGetKeyPassword.set(keyStoreGetKeyPassword);
  }

  public StringProperty keyStoreGetKeyPasswordProperty() {
    return keyStoreGetKeyPassword;
  }

  public String getKeyStoreGetKeyIdentifier() {
    return keyStoreGetKeyIdentifier.get();
  }

  public void setKeyStoreGetKeyIdentifier(String keyStoreGetKeyIdentifier) {
    this.keyStoreGetKeyIdentifier.set(keyStoreGetKeyIdentifier);
  }

  public StringProperty keyStoreGetKeyIdentifierProperty() {
    return keyStoreGetKeyIdentifier;
  }

  public String getKeyStoreGetKeyStorePassword() {
    return keyStoreGetKeyStorePassword.get();
  }

  public void setKeyStoreGetKeyStorePassword(
          String keyStoreGetKeyStorePassword) {
    this.keyStoreGetKeyStorePassword.set(keyStoreGetKeyStorePassword);
  }

  public StringProperty keyStoreGetKeyStorePasswordProperty() {
    return keyStoreGetKeyStorePassword;
  }
}
