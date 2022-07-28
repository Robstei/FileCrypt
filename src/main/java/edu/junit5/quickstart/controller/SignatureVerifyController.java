package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.model.FileHandler;
import edu.junit5.quickstart.signature.PublicSignatureData;
import edu.junit5.quickstart.signature.PublicSignatureKeyData;
import edu.junit5.quickstart.signature.SignatureModel;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class SignatureVerifyController {
  State state = State.getInstance();
  @FXML
  private Label signatureValidateFilePathLabel;
  @FXML
  private Label signatureVerifyKeyPathLabel;


  @FXML
  private void selectFileToVerify() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select File to verify");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setSignatureSignFilePath(
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
  }
}
