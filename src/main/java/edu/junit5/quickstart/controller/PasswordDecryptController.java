package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.OperationResult;
import edu.junit5.quickstart.data.SuccessfulDecryptionOperationResult;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.password.PasswordModel;
import edu.junit5.quickstart.password.PublicPasswordData;
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
import javafx.scene.control.PasswordField;
import javafx.util.Pair;

import java.security.Key;

/**
 * The type Password decrypt controller.
 *
 * @author Robin Steil
 */
public class PasswordDecryptController {
  private final State state = State.getInstance();
  @FXML
  private PasswordField password_decryption;
  @FXML
  private Label encryptFilePathLabel;

  @FXML
  private Button passwordDecryptButton;

  @FXML
  private void initialize() {
    password_decryption.textProperty().addListener(
            (observableValue, oldValue, newValue) -> state.setPasswordForDecryption(
                    newValue));
    encryptFilePathLabel.textProperty().bind(
            state.passwordDecryptionPathProperty());

    passwordDecryptButton.disableProperty().bind(
            state.passwordForDecryptionProperty().isEmpty().or(
                    state.passwordDecryptionPathProperty().isEmpty()));
  }

  @FXML
  private void selectFileToDecrypt() {
    ControllerUtil.setPropertyToFilePath(
            state.passwordDecryptionPathProperty(),
            "Select File to Decrypt with Password",
            new Pair<>("encrypted File (.encrypted)", "*.encrypted"));
  }

  @FXML
  private void decrypt() {
    try {
      PublicValidationData publicValidationData =
              FileHandler.fillDataContainer(new PublicValidationData(),
                                            state.getPasswordDecryptionPath());
      PublicPostEncryptionData publicPostEncryptionData =
              FileHandler.fillDataContainer(new PublicPostEncryptionData(),
                                            state.getPasswordDecryptionPath());
      PublicPasswordData publicPasswordData =
              FileHandler.fillDataContainer(new PublicPasswordData(),
                                            state.getPasswordDecryptionPath());
      char[] password = state.getPasswordForDecryption().toCharArray();
      PasswordModel passwordModel = new PasswordModel();
      Key key = passwordModel.generateKey(password,
                                          publicPasswordData);
      ValidationModel validationModel = new ValidationModel();
      SecretValidationData secretValidationData =
              new SecretValidationData().fill(
                      key);
      boolean valid = validationModel.validate(
              publicPostEncryptionData.getEncryptedBytes(),
              publicValidationData,
              secretValidationData);
      if (!valid) {
        ControllerUtil.showModal(
                new OperationResult(false,
                                    "validation failed. data might be " +
                                            "corrupted"));
        return;
      }

      SecretEncryptionData secretEncryptionData =
              new SecretEncryptionData().fill(
                      key);
      SymmetricEncryptionModel symmetricEncryptionModel =
              new SymmetricEncryptionModel();

      symmetricEncryptionModel.manageSymmetricDecryption(
              publicPostEncryptionData,
              secretEncryptionData);

      String originalFileName =
              state.getPasswordDecryptionPath()
                      .substring(0,
                                 state.getPasswordDecryptionPath()
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
