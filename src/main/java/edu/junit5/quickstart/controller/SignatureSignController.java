package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.OperationResult;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.signature.SignatureModel;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * The Signature sign controller.
 *
 * @author Robin Steil
 */
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
    } catch (Exception e) {
      ControllerUtil.showModal(new OperationResult(false, e.getMessage(), e));
    }
  }
}
