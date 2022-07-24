package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.model.FileHandler;
import edu.junit5.quickstart.model.PublicPostEncryptionData;
import edu.junit5.quickstart.model.SecretEncryptionData;
import edu.junit5.quickstart.model.SymmetricEncryptionModel;
import edu.junit5.quickstart.password.PasswordModel;
import edu.junit5.quickstart.password.PublicPasswordData;
import edu.junit5.quickstart.state.State;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.SecretValidationData;
import edu.junit5.quickstart.validation.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;

import java.io.File;
import java.security.Key;

public class PasswordDecryptController {
  private State state = State.getInstance();
  @FXML
  private PasswordField password_decryption;
  @FXML
  private Label encryptFilePathLabel;

  @FXML
  private void initialize() {
    password_decryption.textProperty().addListener(
            (observableValue, oldValue, newValue) -> {
              state.setPasswordForDecryption(newValue);
            });
    encryptFilePathLabel.textProperty().bind(
            state.passwordDecryptionPathProperty());
  }

  @FXML
  private void selectFileToDecrypt() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File to Decrypt With Password");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setPasswordDecryptionPath(file.getPath());
    }
  }

  @FXML
  private void decrypt() {
    PublicValidationData publicValidationData =
            FileHandler.getPublicValidationDataFromFile(
                    state.getPasswordDecryptionPath());
    PublicPostEncryptionData publicPostEncryptionData =
            FileHandler.getPublicEncryptionDataFromFile(
                    state.getPasswordDecryptionPath());
    PublicPasswordData publicPasswordData =
            FileHandler.getPublicPasswordDataFromFile(
                    state.getPasswordDecryptionPath());
    String password = state.getPasswordForDecryption();
    PasswordModel passwordModel = new PasswordModel();
    Key key = passwordModel.generateKey(password,
                                        publicPasswordData);
    Validator validator = new Validator();
    SecretValidationData secretValidationData = new SecretValidationData(key);
    boolean valid = validator.validate(
            publicPostEncryptionData.getEncryptedBytes(),
            publicValidationData,
            secretValidationData);
    if (!valid) {
      ControllerUtil.showErrorModal();
      return;
    }

    SecretEncryptionData secretEncryptionData = new SecretEncryptionData(key);
    SymmetricEncryptionModel symmetricEncryptionModel =
            new SymmetricEncryptionModel();
    symmetricEncryptionModel.manageSymmetricDecryption(
            publicPostEncryptionData,
            secretEncryptionData);
    FileHandler.saveByteArrayAsFile(symmetricEncryptionModel.getResult(),
                                    state.getPasswordDecryptionPath() +
                                            ".decrypted");

  }
}
