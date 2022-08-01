package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.Transformation;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.password.PasswordModel;
import edu.junit5.quickstart.password.PublicPasswordData;
import edu.junit5.quickstart.state.State;
import edu.junit5.quickstart.symmetricEncryption.PublicPostEncryptionData;
import edu.junit5.quickstart.symmetricEncryption.PublicPreEncryptionData;
import edu.junit5.quickstart.symmetricEncryption.SymmetricEncryptionModel;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.Validator;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
  private Button passwordEncryptButton;

  @FXML
  private void initialize() {


    BooleanBinding disableKeyLength = new BooleanBinding() {
      {
        super.bind(state.passwordEncryptionAlgorithmProperty());
      }

      @Override
      protected boolean computeValue() {
        String algorithm =
                state.passwordEncryptionAlgorithmProperty().getValue();
        return algorithm.equals(
                "PBEWithSHA256And128BitAES-CBC-BC") || algorithm.equals(
                "PBEWithSHAAnd40BitRC4");
      }
    };

    disableKeyLength.addListener(((observableValue, oldValue, newValue) -> {
      if (newValue) {
        state.setPasswordEncryptionKeyLength(null);
      }
    }));
    for (Toggle toggle : password_keysize.getToggles()) {
      ((RadioButton) toggle).disableProperty().bind(disableKeyLength);
    }
    ControllerUtil.bindToggleGroupToProperty(password_keysize,
                                             state.passwordEncryptionKeyLengthProperty());
    ControllerUtil.bindToggleGroupToProperty(password_validation,
                                             state.passwordEncryptionValidationProperty());

    password_algorithm.selectedToggleProperty().addListener(
            (observableValue, oldValue, newValue) -> {
              if (newValue.getUserData().equals(
                      "PBEWithSHA256And128BitAES-CBC-BC") || newValue.getUserData().equals(
                      "PBEWithSHAAnd40BitRC4")) {
                state.setPasswordGenerationAlgorithm(
                        (String) newValue.getUserData());
                state.setPasswordEncryptionAlgorithm(
                        (String) newValue.getUserData());
                state.setPasswordEncryptionMode("");
                state.setPasswordEncryptionPadding("");
                state.setPasswordEncryptionKeyLength(null);
              } else if (newValue.getUserData().equals(
                      "SCRYPT-AES-GCM")) {
                state.setPasswordGenerationAlgorithm("SCRYPT");
                state.setPasswordEncryptionAlgorithm("AES");
                state.setPasswordEncryptionMode("GCM");
                state.setPasswordEncryptionPadding("NoPadding");
              }
            });
    for (Toggle toggle : password_algorithm.getToggles()) {
      if (toggle.getUserData().equals(
              state.getPasswordEncryptionAlgorithm())) {
        password_algorithm.selectToggle(toggle);
      }
    }

    password_encryption.textProperty().bindBidirectional(
            state.passwordForEncryptionProperty());
    encryptFilePathLabel.textProperty().bind(
            state.passwordEncryptionPathProperty());

    passwordEncryptButton.disableProperty().bind(
            state.passwordEncryptionAlgorithmProperty().isEmpty().or(
                    state.passwordEncryptionValidationProperty().isEmpty().or(
                            state.passwordForEncryptionProperty().isEmpty().or(
                                    state.passwordEncryptionPathProperty().isEmpty().or(
                                            disableKeyLength.not().and(
                                                    state.passwordEncryptionKeyLengthProperty().isNull()))))));
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
    int keyLength;
    if (state.getPasswordEncryptionKeyLength() == null) {
      keyLength = -1;
    } else {
      keyLength = Integer.parseInt(state.getPasswordEncryptionKeyLength());
    }
    Key key = passwordModel.generateKey(password,
                                        state.getPasswordGenerationAlgorithm(),
                                        keyLength);
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
