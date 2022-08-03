package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.OperationResult;
import edu.junit5.quickstart.io.FileHandler;
import edu.junit5.quickstart.signature.SignatureModel;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.util.Pair;

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
                    state.signatureSignFilePathProperty().isEmpty()));
  }

  @FXML
  private void selectFileToSign() {
    ControllerUtil.setPropertyToFilePath(
            state.signatureSignFilePathProperty(),
            "Select File to Sign",
            new Pair<>("File to sign",
                       "*"));
  }

  @FXML
  private void sign() {
    try {
      SignatureModel signatureModel = new SignatureModel();
      signatureModel.generateKeys(state.getSignatureSignAlgorithm());
      byte[] fileAsBytes = FileHandler.getFileAsByteArray(
              state.getSignatureSignFilePath());
      signatureModel.sign(fileAsBytes);

      String signedFilePath = state.getSignatureSignFilePath() + ".signed";
      FileHandler.saveDataToXMLFile(
              signedFilePath,
              signatureModel.getPublicSignatureData());

      String publicSignatureKeyPath = state.getSignatureSignFilePath() +
              ".publicsignaturekey";
      FileHandler.saveDataToXMLFile(
              publicSignatureKeyPath,
              signatureModel.getPublicSignatureKeyData());

      String privateSignatureKeyPath =
              state.getSignatureSignFilePath() + ".privatesignaturekey";
      FileHandler.saveDataToXMLFile(privateSignatureKeyPath,
                                    signatureModel.getSecretSignatureKeyData());

      ControllerUtil.showModal(new OperationResult(true,
                                                   "Created signed file at " + signedFilePath + ".\n" +
                                                           "Created public " +
                                                           "key file at: " + publicSignatureKeyPath + "\n" +
                                                           "Created private " +
                                                           "key file at " + privateSignatureKeyPath));
    } catch (Exception e) {
      ControllerUtil.showModal(new OperationResult(false, e.getMessage(), e));
    }
  }
}
