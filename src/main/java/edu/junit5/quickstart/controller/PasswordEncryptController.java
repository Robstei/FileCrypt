package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.model.FileHandler;
import edu.junit5.quickstart.model.PublicEncryptionData;
import edu.junit5.quickstart.model.SecretEncryptionData;
import edu.junit5.quickstart.model.SymmetricEncryptionModel;
import edu.junit5.quickstart.password.PasswordModel;
import edu.junit5.quickstart.password.PublicPasswordData;
import edu.junit5.quickstart.state.State;
import edu.junit5.quickstart.state.Transformation;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.SecretValidationData;
import edu.junit5.quickstart.validation.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptController {

  State state = State.getInstance();
  @FXML
  private Label encryptFilePathLabel;
  @FXML
  private ToggleGroup password_algorithm;
  @FXML
  private PasswordField password_encryption;

  @FXML
  private void initialize() {
    encryptFilePathLabel.textProperty().bind(
            state.passwordEncryptionPathProperty());
    password_algorithm.selectedToggleProperty().addListener(
            (observableValue, oldValue, newValue) -> {
              if (newValue.getUserData().equals(
                      "PBEWithSHA256And128BitAES") && newValue.getUserData().equals(
                      "PBEWithSHAAnd40BitRC4")) {
                state.setPasswordEncryptionAlgorithmName(
                        (String) newValue.getUserData());
                state.setPasswordEncryptionMode("");
                state.setPasswordEncryptionPadding("");
              } else if (newValue.getUserData().equals(
                      "AES 256 Bit, GCM, SCRYPT")) {
                //TODO: change state. maybe combine symmetric encryption from
                // password and encryption view. there needs to be a
                // differentiation between the password generation algorithm and
                // the encryption algorithm when using a password
              }
            });

    password_encryption.textProperty().addListener(
            (observableValue, oldValue, newValue) -> {
              state.setPasswordForEncryption(newValue);
            });
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
    try {
      String password = state.getPasswordForEncryption();
      String algorithm = state.getPasswordEncryptionAlgorithmName();
      PasswordModel passwordModel = new PasswordModel();
      Key key = passwordModel.generateKey(password, algorithm);
      PublicPasswordData publicPasswordData =
              passwordModel.getPublicPasswordData();

      //read file
      byte[] fileAsBytes = FileHandler.getFileAsByteArray(
              state.getPasswordEncryptionPath());
      //encrypt

      Transformation transformation = new Transformation(
              state.getPasswordEncryptionAlgorithmName(),
              state.getPasswordEncryptionMode(),
              state.getPasswordEncryptionPadding());

      //TODO: find a better way to create AES Parameters although the name of
      // the algorithm might be longer than just AES
      AlgorithmParameterGenerator algorithmParameterGenerator =
              AlgorithmParameterGenerator.getInstance(
                      "AES",
                      new BouncyCastleProvider());
      AlgorithmParameters algorithmParameters =
              algorithmParameterGenerator.generateParameters();

      //TODO: implement validation options. UI, bindings are missing
      SymmetricEncryptionModel symmetricEncryptionModel =
              new SymmetricEncryptionModel();
      symmetricEncryptionModel.manageSymmetricEncryption(fileAsBytes,
                                                         transformation,
                                                         key,
                                                         algorithmParameters);
      byte[] encryptedBytes = symmetricEncryptionModel.getResult();
      PublicEncryptionData publicEncryptionData =
              symmetricEncryptionModel.getPublicEncryptionData();
      SecretEncryptionData secretEncryptionData =
              symmetricEncryptionModel.getSecretEncryptionData();

      Validator validator = new Validator();
      validator.generateValidation(
              encryptedBytes, state.getSymmetricEncryptionValidation());
      PublicValidationData publicValidationData =
              validator.getPublicValidationData();
      SecretValidationData secretValidationData =
              validator.getSecretValidationData();
      FileHandler.savePublicData(encryptedBytes,
                                 publicEncryptionData,
                                 publicValidationData,
                                 publicPasswordData,
                                 state.getPasswordEncryptionPath());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
