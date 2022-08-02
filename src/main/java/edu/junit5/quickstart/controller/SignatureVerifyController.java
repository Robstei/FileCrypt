package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.OperationResult;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.signature.PublicSignatureData;
import edu.junit5.quickstart.signature.PublicSignatureKeyData;
import edu.junit5.quickstart.signature.SignatureModel;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * The Signature verify controller.
 *
 * @author Robin Steil
 */
public class SignatureVerifyController {
  private final State state = State.getInstance();
  @FXML
  private Label signatureVerifyFilePathLabel;
  @FXML
  private Label signatureVerifyKeyPathLabel;
  @FXML
  private Button signatureVerifyButton;


  @FXML
  private void initialize() {
    signatureVerifyFilePathLabel.textProperty().bind(
            state.signatureVerifyFilePathProperty());
    signatureVerifyKeyPathLabel.textProperty().bind(
            state.signatureVerifyKeyFilePathProperty());

    signatureVerifyButton.disableProperty().bind(
            state.signatureVerifyFilePathProperty().isEmpty().or(
                    state.signatureVerifyKeyFilePathProperty().isEmpty()));
  }

  @FXML
  private void selectFileToVerify() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File to verify");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setSignatureVerifyFilePath(
              file.getPath());
    }
  }

  @FXML
  private void selectKeyFileForSignatureValidation() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Key File to verify with");
    File file = fileChooser.showOpenDialog(null);
    if (file != null) {
      state.setSignatureVerifyKeyFilePath(
              file.getPath());
    }
  }

  @FXML
  private void verify() {
    try {
      SignatureModel signatureModel = new SignatureModel();
      PublicSignatureData publicSignatureData =
              FileHandler.fillDataContainer(new PublicSignatureData(),
                                            state.getSignatureVerifyFilePath());
      PublicSignatureKeyData publicSignatureKeyData =
              FileHandler.fillDataContainer(new PublicSignatureKeyData(),
                                            state.getSignatureVerifyKeyFilePath());
      boolean verified = signatureModel.verify(publicSignatureData,
                                               publicSignatureKeyData);
      if (verified) {
        FileHandler.saveByteArrayAsFile(publicSignatureData.getSignedBytes(),
                                        state.getSignatureVerifyFilePath() +
                                                ".verified");
      }
    } catch (Exception e) {
      ControllerUtil.showModal(new OperationResult(false, e.getMessage(), e));
    }
  }
}
