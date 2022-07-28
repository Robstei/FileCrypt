package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.model.FileHandler;
import edu.junit5.quickstart.model.PublicPostEncryptionData;
import edu.junit5.quickstart.model.SecretEncryptionData;
import edu.junit5.quickstart.model.SymmetricEncryptionModel;
import edu.junit5.quickstart.state.State;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.SecretValidationData;
import edu.junit5.quickstart.validation.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class SymmetricEncryptionDecryptController {

  private final State state = State.getInstance();
  @FXML
  private Label decryptFilePathLabel;
  @FXML
  private Label keyFilePathLabel;

  @FXML
  private Button encryptionDecryptButton;

  @FXML
  private void initialize() {
    
    decryptFilePathLabel.textProperty().bind(
            state.symmetricEncryptionDecryptFilePathProperty());
    keyFilePathLabel.textProperty().bind(
            state.symmetricEncryptionKeyFilePathProperty());

    encryptionDecryptButton.disableProperty().bind(
            state.symmetricEncryptionDecryptFilePathProperty().isEmpty().or(
                    state.symmetricEncryptionKeyFilePathProperty().isEmpty()));
  }

  @FXML
  private void selectFileToDecrypt() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File to Decrypt");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setSymmetricEncryptionDecryptFilePath(
              file.getPath());
    }
  }

  @FXML
  private void selectKeyFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Key to Decrypt");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setSymmetricEncryptionKeyFilePath(
              file.getPath());
    }
  }

  private boolean isFormFilledOut() {
    boolean formFilledOut = true;

    if (decryptFilePathLabel.getText() == null) {
      formFilledOut = false;
    }

    if (keyFilePathLabel.getText() == null) {
      formFilledOut = false;
    }
    return formFilledOut;
  }

  @FXML
  private void decrypt() {
    if (!isFormFilledOut()) {
      return;
    }
    PublicPostEncryptionData publicPostEncryptionData =
            FileHandler.fillDataContainer(new PublicPostEncryptionData(),
                                          state.getSymmetricEncryptionDecryptFilePath());
    PublicValidationData publicValidationData =
            FileHandler.fillDataContainer(new PublicValidationData(),
                                          state.getSymmetricEncryptionDecryptFilePath());
    SecretEncryptionData secretEncryptionData =
            FileHandler.fillDataContainer(new SecretEncryptionData(),
                                          state.getSymmetricEncryptionKeyFilePath());
    SecretValidationData secretValidationData =
            FileHandler.fillDataContainer(new SecretValidationData(),
                                          state.getSymmetricEncryptionKeyFilePath());

    SymmetricEncryptionModel symmetricEncryptionModel =
            new SymmetricEncryptionModel();
    Validator validator = new Validator();
    boolean valid = validator.validate(
            publicPostEncryptionData.getEncryptedBytes(),
            publicValidationData,
            secretValidationData);
    if (!valid) {
      return;
    }
    symmetricEncryptionModel.manageSymmetricDecryption(publicPostEncryptionData,
                                                       secretEncryptionData);
    FileHandler.saveByteArrayAsFile(symmetricEncryptionModel.getResult(),
                                    state.getSymmetricEncryptionDecryptFilePath() + ".decrypted");
  }
}
