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
import javafx.util.Pair;

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
    ControllerUtil.setPropertyToFilePath(
            state.signatureVerifyFilePathProperty(),
            "Select File to verify",
            new Pair<>("File to verify (*.signed", "*.signed"));
  }

  @FXML
  private void selectKeyFileForSignatureValidation() {
    ControllerUtil.setPropertyToFilePath(
            state.signatureVerifyKeyFilePathProperty(),
            "Select Key File to verify with",
            new Pair<>("Key File to verify with (*.publicsignaturekey",
                       "*.publicsignaturekey"));
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
        String originalFileName = state.getSignatureVerifyFilePath().substring(
                0, state.getSignatureVerifyFilePath().lastIndexOf(".signed"));
        FileHandler.saveByteArrayAsFile(publicSignatureData.getSignedBytes(),
                                        originalFileName);
        ControllerUtil.showModal(new OperationResult(true,
                                                     "Signature is valid. " +
                                                             "Created " +
                                                             "original file " +
                                                             "at " + originalFileName));
      } else {
        ControllerUtil.showModal(new OperationResult(false,
                                                     "Verification of " +
                                                             "signature " +
                                                             "failed."));
      }
    } catch (Exception e) {
      ControllerUtil.showModal(new OperationResult(false, e.getMessage(), e));
    }
  }
}
