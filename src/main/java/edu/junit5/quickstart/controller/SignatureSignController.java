package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.model.FileHandler;
import edu.junit5.quickstart.signature.SignatureModel;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class SignatureSignController {
  private State state = State.getInstance();

  @FXML
  private Label signatureSignFilePathLabel;

  @FXML
  private void initialize() {
    signatureSignFilePathLabel.textProperty().bind(
            state.signatureSignFilePathProperty());
  }

  @FXML
  private void selectFileToSign() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File to Decrypt");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setSignatureSignFilePath(
              file.getPath());
    }
  }

  @FXML
  private void sign() {
    SignatureModel signatureModel = new SignatureModel();
    signatureModel.generateKeys(state.getSignatureSignAlgorithm());
    byte[] fileAsBytes = FileHandler.getFileAsByteArray(
            state.getSignatureSignFilePath());
    signatureModel.sign(fileAsBytes);
    FileHandler.saveDataToXMLFile(state.getSignatureSignFilePath() + ".signed",
                                  signatureModel.getPublicSignatureData());
    FileHandler.saveDataToXMLFile(
            state.getSignatureSignFilePath() + ".publicsigkey",
            signatureModel.getPublicSignatureKeyData());
    FileHandler.saveDataToXMLFile(
            state.getSignatureSignFilePath() + ".secretsignaturekey",
            signatureModel.getSecretSignatureKeyData());
  }
}
