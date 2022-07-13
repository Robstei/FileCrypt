package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.mode.Mode;
import edu.junit5.quickstart.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

public class SymmetricEncryptionEncryptController {

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


    private void updateValidPaddings(String mode) {
        Model model = Model.getInstance();
        ArrayList<String> validPaddingNames = model.getModeByKey(mode).getValidPaddingNames();
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
    private void initialize() {

        ControllerUtil.bindToggleGroupToProperty(encryption_algorithm, Model.getInstance().getProperties().symmetricEncryptionAlgorithmProperty());
        Model.getInstance().getProperties().symmetricEncryptionModeProperty().addListener((observableValue, oldValue, newValue) -> {

            Mode mode = Model.getInstance().getModeByKey(newValue);

            for (Toggle toggle : encryption_padding.getToggles()) {
                if (mode.isValidPadding((String) toggle.getUserData())) {
                    ((RadioButton) toggle).setDisable(false);
                } else {
                    ((RadioButton) toggle).setDisable(true);
                    toggle.setSelected(false);
                }
            }
        });
        ControllerUtil.bindToggleGroupToProperty(encryption_mode, Model.getInstance().getProperties().symmetricEncryptionModeProperty());
        ControllerUtil.bindToggleGroupToProperty(encryption_keysize, Model.getInstance().getProperties().symmetricEncryptionKeySizeProperty());
        ControllerUtil.bindToggleGroupToProperty(encryption_padding, Model.getInstance().getProperties().symmetricEncryptionPaddingProperty());
        ControllerUtil.bindToggleGroupToProperty(encryption_validation, Model.getInstance().getProperties().symmetricEncryptionValidationProperty());
        encryptFilePathLabel.textProperty().bind(Model.getInstance().getProperties().filePathProperty());
        encryption_mode.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            updateValidPaddings((String) newValue.getUserData());
        });

        String mode = Model.getInstance().getProperties().getSymmetricEncryptionMode();
        updateValidPaddings(mode);
    }

    @FXML
    private void selectFileToEncrypt() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Encrypt");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Model.getInstance().getProperties().setSymmetricEncryptionEncryptFilePath(file.getPath());
        }
    }


    private boolean isFormFilledOut() {
        boolean formFilledOut = true;

        if (encryption_algorithm.selectedToggleProperty() == null) {
            formFilledOut = false;
        }
        if (encryption_mode.selectedToggleProperty() == null) {
            formFilledOut = false;
        }

        if (encryption_padding.selectedToggleProperty() == null) {
            formFilledOut = false;
        }

        if (encryption_validation.selectedToggleProperty() == null) {
            formFilledOut = false;
        }

        if (encryptFilePathLabel.getText() == null) {
            formFilledOut = false;
        }

        return formFilledOut;
    }

    @FXML
    private void encrypt() {
        if (isFormFilledOut()) {
            Model.getInstance().manageSymmetricEncryption();
        }
    }
}
