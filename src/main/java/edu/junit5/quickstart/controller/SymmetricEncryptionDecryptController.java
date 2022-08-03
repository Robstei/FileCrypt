package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.FailedValidationOperationResult;
import edu.junit5.quickstart.data.OperationResult;
import edu.junit5.quickstart.data.SuccessfulDecryptionOperationResult;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.state.State;
import edu.junit5.quickstart.symmetricEncryption.PublicPostEncryptionData;
import edu.junit5.quickstart.symmetricEncryption.SecretEncryptionData;
import edu.junit5.quickstart.symmetricEncryption.SymmetricEncryptionModel;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.SecretValidationData;
import edu.junit5.quickstart.validation.ValidationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Pair;

/**
 * The Symmetric encryption decrypt controller.
 *
 * @author Robin Steil
 */
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
    ControllerUtil.setPropertyToFilePath(
            state.symmetricEncryptionDecryptFilePathProperty(),
            "Select File to Decrpyt",
            new Pair<>("encrypted File (.encrypted)", "*.encrypted"));
  }

  @FXML
  private void selectKeyFile() {
    ControllerUtil.setPropertyToFilePath(
            state.symmetricEncryptionKeyFilePathProperty(),
            "Select Key to Decrypt",
            new Pair<>("keyfile (.encryptionkey)", "*.encryptionkey"));
  }

  @FXML
  private void decrypt() {
    try {
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
              FileHandler.doesXMLFileContainKeys(
                      new SecretValidationData().getMapKeys(),
                      state.getSymmetricEncryptionKeyFilePath()) ?
                      FileHandler.fillDataContainer(
                              new SecretValidationData(),
                              state.getSymmetricEncryptionKeyFilePath()) :
                      new SecretValidationData();

      ValidationModel validationModel = new ValidationModel();
      boolean valid = validationModel.validate(
              publicPostEncryptionData.getEncryptedBytes(),
              publicValidationData,
              secretValidationData);

      if (!valid) {
        ControllerUtil.showModal(
                new FailedValidationOperationResult(
                        publicValidationData.getName()));
        return;
      }

      SymmetricEncryptionModel symmetricEncryptionModel =
              new SymmetricEncryptionModel();

      symmetricEncryptionModel.manageSymmetricDecryption(
              publicPostEncryptionData,
              secretEncryptionData);


      String originalFileName =
              state.getSymmetricEncryptionDecryptFilePath()
                      .substring(0,
                                 state.getSymmetricEncryptionDecryptFilePath()
                                         .lastIndexOf(".encrypted"));

      FileHandler.saveByteArrayAsFile(symmetricEncryptionModel.getResult(),
                                      originalFileName);
      ControllerUtil.showModal(
              new SuccessfulDecryptionOperationResult(originalFileName));
    } catch (Exception e) {
      ControllerUtil.showModal(new OperationResult(false, e.getMessage(), e));
    }
  }
}
