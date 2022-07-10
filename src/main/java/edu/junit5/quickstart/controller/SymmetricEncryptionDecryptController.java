package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class SymmetricEncryptionDecryptController {

    @FXML
    private Label decryptFilePathLabel;

    @FXML
    private Label keyFilePathLabel;

    @FXML
    private void initialize() {
        decryptFilePathLabel.textProperty().bind(Model.getInstance().getProperties().symmetricEncryptionDecryptFilePathProperty());
        keyFilePathLabel.textProperty().bind(Model.getInstance().getProperties().symmetricEncryptionKeyFilePathProperty());

    }

    @FXML
    private void selectFileToDecrypt() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Decrypt");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Model.getInstance().getProperties().setSymmetricEncryptionDecryptFilePath(file.getPath());
        }
    }

    @FXML
    private void selectKeyFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Key to Decrypt");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Model.getInstance().getProperties().setSymmetricEncryptionKeyFilePath(file.getPath());
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
        if (isFormFilledOut()) {
            Model.getInstance().manageSymmetricDecryption();
        }
    }
}
