package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.signature.SignatureModel;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

public class SignatureSignController {
  private final State state = State.getInstance();

  @FXML
  private ToggleGroup signature_algorithm;

  @FXML
  private Label signatureSignFilePathLabel;

  @FXML
  private Button signatureSignButton;

  @FXML
  private void initialize() {
    ControllerUtil.bindToggleGroupToProperty(signature_algorithm,
                                             state.signatureSignAlgorithmProperty());

    signatureSignFilePathLabel.textProperty().bind(
            state.signatureSignFilePathProperty());

    signatureSignButton.disableProperty().bind(
            state.signatureSignAlgorithmProperty().isEmpty().or(
                    state.signatureVerifyFilePathProperty().isEmpty()));
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
    try {
      SignatureModel signatureModel = new SignatureModel();
      signatureModel.generateKeys(state.getSignatureSignAlgorithm());
      byte[] fileAsBytes = FileHandler.getFileAsByteArray(
              state.getSignatureSignFilePath());
      signatureModel.sign(fileAsBytes);
      FileHandler.saveDataToXMLFile(
              state.getSignatureSignFilePath() + ".signed",
              signatureModel.getPublicSignatureData());
      FileHandler.saveDataToXMLFile(
              state.getSignatureSignFilePath() + ".publicsigkey",
              signatureModel.getPublicSignatureKeyData());
      FileHandler.saveDataToXMLFile(
              state.getSignatureSignFilePath() + ".secretsignaturekey",
              signatureModel.getSecretSignatureKeyData());
    } catch (NoSuchAlgorithmException | IOException | SignatureException |
             ParserConfigurationException | NoSuchProviderException |
             InvalidKeyException | TransformerException e) {
      throw new RuntimeException(e);
    }
  }
}
