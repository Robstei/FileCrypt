package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.model.FileHandler;
import edu.junit5.quickstart.model.PublicPostEncryptionData;
import edu.junit5.quickstart.model.PublicPreEncryptionData;
import edu.junit5.quickstart.model.SymmetricEncryptionModel;
import edu.junit5.quickstart.password.PasswordModel;
import edu.junit5.quickstart.password.PublicPasswordData;
import edu.junit5.quickstart.state.State;
import edu.junit5.quickstart.state.Transformation;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import java.io.File;
import java.security.Key;

public class PasswordEncryptController {

  State state = State.getInstance();
  @FXML
  private Label encryptFilePathLabel;
  @FXML
  private ToggleGroup password_algorithm;

  @FXML
  private ToggleGroup password_keysize;
  @FXML
  private ToggleGroup password_validation;
  @FXML
  private PasswordField password_encryption;

  @FXML
  private void initialize() {
    for (Toggle toggle : password_algorithm.getToggles()) {
      if (toggle.getUserData().equals(
              state.getPasswordEncryptionAlgorithm())) {
        password_algorithm.selectToggle(toggle);
      }
    }
    password_algorithm.selectedToggleProperty().addListener(
            (observableValue, oldValue, newValue) -> {
              if (newValue.getUserData().equals(
                      "PBEWithSHA256And128BitAES") || newValue.getUserData().equals(
                      "PBEWithSHAAnd40BitRC4")) {
                state.setPasswordGenerationAlgorithm(
                        (String) newValue.getUserData());
                state.setPasswordEncryptionAlgorithm(
                        (String) newValue.getUserData());
                state.setPasswordEncryptionMode("");
                state.setPasswordEncryptionPadding("");
              } else if (newValue.getUserData().equals(
                      "SCRYPT-AES-GCM")) {
                state.setPasswordGenerationAlgorithm("SCRYPT");
                state.setPasswordEncryptionAlgorithm("AES");
                state.setPasswordEncryptionMode("GCM");
                state.setPasswordEncryptionPadding("NoPadding");
                //TODO: change state. maybe combine symmetric encryption from
                // password and encryption view. there needs to be a
                // differentiation between the password generation algorithm and
                // the encryption algorithm when using a password
              }
            });

    ControllerUtil.bindToggleGroupToProperty(password_keysize,
                                             state.passwordEncryptionKeyLengthProperty());
    ControllerUtil.bindToggleGroupToProperty(password_validation,
                                             state.passwordEncryptionValidationProperty());
    password_encryption.textProperty().bindBidirectional(
            state.passwordForEncryptionProperty());
    encryptFilePathLabel.textProperty().bind(
            state.passwordEncryptionPathProperty());
  }

  @FXML
  private void selectFileToEncrypt() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File to Encrypt With Password");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setPasswordEncryptionPath(file.getPath());
    }
  }

  @FXML
  private void encrypt() {
    String password = state.getPasswordForEncryption();
    String algorithm = state.getPasswordEncryptionAlgorithm();
    PasswordModel passwordModel = new PasswordModel();
    Key key = passwordModel.generateKey(password,
                                        state.getPasswordGenerationAlgorithm(),
                                        Integer.parseInt(
                                                state.getPasswordEncryptionKeyLength()));
    PublicPasswordData publicPasswordData =
            passwordModel.getPublicPasswordData();

    byte[] fileAsBytes = FileHandler.getFileAsByteArray(
            state.getPasswordEncryptionPath());

    Transformation transformation = new Transformation(
            state.getPasswordEncryptionAlgorithm(),
            state.getPasswordEncryptionMode(),
            state.getPasswordEncryptionPadding());

    SymmetricEncryptionModel symmetricEncryptionModel =
            new SymmetricEncryptionModel();
    symmetricEncryptionModel.manageSymmetricEncryption(
            new PublicPreEncryptionData(fileAsBytes, transformation, -1),
            key);
    byte[] encryptedBytes = symmetricEncryptionModel.getResult();
    PublicPostEncryptionData publicPostEncryptionData =
            symmetricEncryptionModel.getPublicEncryptionData();

    Validator validator = new Validator();
    validator.generateValidation(
            encryptedBytes, state.getPasswordEncryptionValidation(), key);

    PublicValidationData publicValidationData =
            validator.getPublicValidationData();
    FileHandler.saveDataToXMLFile(
            state.getPasswordEncryptionPath() + ".encrypted",
            publicPostEncryptionData,
            publicValidationData,
            publicPasswordData
    );
  }
}
