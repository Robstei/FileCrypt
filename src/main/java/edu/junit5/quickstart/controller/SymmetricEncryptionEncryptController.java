package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.Transformation;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.mode.Mode;
import edu.junit5.quickstart.mode.Modes;
import edu.junit5.quickstart.state.State;
import edu.junit5.quickstart.symmetricEncryption.PublicPostEncryptionData;
import edu.junit5.quickstart.symmetricEncryption.PublicPreEncryptionData;
import edu.junit5.quickstart.symmetricEncryption.SecretEncryptionData;
import edu.junit5.quickstart.symmetricEncryption.SymmetricEncryptionModel;
import edu.junit5.quickstart.validation.PublicValidationData;
import edu.junit5.quickstart.validation.SecretValidationData;
import edu.junit5.quickstart.validation.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

public class SymmetricEncryptionEncryptController {

  State state = State.getInstance();
  @FXML
  private Label encryptFilePathLabel;
  @FXML
  private ToggleGroup encryption_algorithm;
  @FXML
  private ToggleGroup encryption_mode;
  @FXML
  private ToggleGroup encryption_padding;
  @FXML
  private ToggleGroup encryption_keysize;
  @FXML
  private ToggleGroup encryption_validation;

  @FXML
  private Button encryptionEncryptButton;


  @FXML
  private void initialize() {


    ControllerUtil.bindToggleGroupToProperty(encryption_algorithm,
                                             state.symmetricEncryptionAlgorithmProperty());
    state.symmetricEncryptionModeProperty().addListener(
            (observableValue, oldValue, newValue) -> {

              Modes modes = new Modes();
              Mode mode = modes.getModeByName(newValue);

              for (Toggle toggle : encryption_padding.getToggles()) {
                if (mode.isValidPadding(
                        (String) toggle.getUserData())) {
                  ((RadioButton) toggle).setDisable(false);
                } else {
                  ((RadioButton) toggle).setDisable(true);
                  toggle.setSelected(false);
                }
              }
            });
    ControllerUtil.bindToggleGroupToProperty(encryption_mode,
                                             state.symmetricEncryptionModeProperty());
    ControllerUtil.bindToggleGroupToProperty(encryption_keysize,
                                             state.symmetricEncryptionKeyLengthProperty());
    ControllerUtil.bindToggleGroupToProperty(encryption_padding,
                                             state.symmetricEncryptionPaddingProperty());
    ControllerUtil.bindToggleGroupToProperty(encryption_validation,
                                             state.symmetricEncryptionValidationProperty());
    encryptFilePathLabel.textProperty().bind(
            state.filePathProperty());
    encryption_mode.selectedToggleProperty().addListener((observableValue,
                                                          oldValue,
                                                          newValue) -> {
      updateValidPaddings((String) newValue.getUserData());
    });

    String mode =
            state.getSymmetricEncryptionMode();
    updateValidPaddings(mode);

    encryptionEncryptButton.disableProperty().bind(
            state.symmetricEncryptionAlgorithmProperty().isEmpty().or(
                    state.symmetricEncryptionModeProperty().isEmpty()).or(
                    state.symmetricEncryptionPaddingProperty().isEmpty()).or(
                    state.symmetricEncryptionKeyLengthProperty().isEmpty()).or(
                    state.symmetricEncryptionValidationProperty().isEmpty()).or(
                    state.symmetricEncryptionEncryptFilePathProperty().isEmpty()));

  }

  private void updateValidPaddings(String mode) {
    Modes modes = new Modes();
    ArrayList<String> validPaddingNames =
            modes.getModeByName(mode).getValidPaddingNames();
    for (Toggle toggle : encryption_padding.getToggles()) {
      if (validPaddingNames.contains(toggle.getUserData())) {
        ((RadioButton) toggle).setDisable(false);
        if (validPaddingNames.size() == 1) {
          toggle.setSelected(true);
        }
      } else {
        ((RadioButton) toggle).setDisable(true);
        toggle.setSelected(false);
      }
    }
  }

  @FXML
  private void selectFileToEncrypt() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File to Encrypt");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setSymmetricEncryptionEncryptFilePath(
              file.getPath());
    }
  }

  @FXML
  private void encrypt() {
    byte[] fileAsByteArray =
            FileHandler.getFileAsByteArray(
                    state.getSymmetricEncryptionEncryptFilePath());

    Transformation transformation =
            new Transformation(
                    state.getSymmetricEncryptionAlgorithm(),
                    state.getSymmetricEncryptionMode(),
                    state.getSymmetricEncryptionPadding());

    int keySize = Integer.valueOf(state.getSymmetricEncryptionKeyLength());
    String symmetricEncryptionValidation =
            state.getSymmetricEncryptionValidation();

    SymmetricEncryptionModel symmetricEncryptionModel =
            new SymmetricEncryptionModel();
    symmetricEncryptionModel.manageSymmetricEncryption(
            new PublicPreEncryptionData(fileAsByteArray, transformation,
                                        keySize));

    byte[] encryptedBytes = symmetricEncryptionModel.getResult();
    PublicPostEncryptionData publicPostEncryptionData =
            symmetricEncryptionModel.getPublicEncryptionData();
    SecretEncryptionData secretEncryptionData =
            symmetricEncryptionModel.getSecretEncryptionData();

    Validator validator = new Validator();
    validator.generateValidation(encryptedBytes,
                                 state.getSymmetricEncryptionValidation());
    PublicValidationData publicValidationData =
            validator.getPublicValidationData();
    SecretValidationData secretValidationData =
            validator.getSecretValidationData();

    FileHandler.saveDataToXMLFile(
            state.getSymmetricEncryptionEncryptFilePath() + ".encrypted",
            publicPostEncryptionData,
            publicValidationData
    );
    FileHandler.saveDataToXMLFile(
            state.getSymmetricEncryptionEncryptFilePath() + ".encryptionkey",
            secretEncryptionData,
            secretValidationData
    );
  }
}
