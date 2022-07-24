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


    symmetricEncryptionEncryptFilePath = new SimpleStringProperty();
    symmetricEncryptionDecryptFilePath = new SimpleStringProperty();
    symmetricEncryptionKeyFilePath = new SimpleStringProperty();
    passwordForEncryption = new SimpleStringProperty();
    passwordEncryptionMode = new SimpleStringProperty("");
    passwordEncryptionPadding = new SimpleStringProperty("");
    passwordEncryptionPath = new SimpleStringProperty();
    passwordForDecryption = new SimpleStringProperty();
    passwordDecryptionPath = new SimpleStringProperty();
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
}
