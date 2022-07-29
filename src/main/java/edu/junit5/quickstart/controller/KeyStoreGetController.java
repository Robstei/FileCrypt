package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.KeyStoreModal;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;


public class KeyStoreGetController {

  State state = State.getInstance();
  @FXML
  private Label keyStoreGetKeyStoreFilePathLabel;
  @FXML
  private PasswordField keyStoreGetKeyStorePassword;
  @FXML
  private PasswordField keyStoreGetKeyPassword;

  @FXML
  private TextField keyStoreGetIdentifier;

  @FXML
  private Button keyStoreSaveButton;

  @FXML
  public void initialize() {
    keyStoreGetKeyStoreFilePathLabel.textProperty().bind(
            state.keyStoreGetFilePathProperty());
    keyStoreGetKeyStorePassword.textProperty().bindBidirectional(
            state.keyStoreGetKeyStorePasswordProperty());
    keyStoreGetKeyPassword.textProperty().bindBidirectional(
            state.keyStoreGetPasswordProperty());
    keyStoreGetIdentifier.textProperty().bindBidirectional(
            state.keyStoreGetIdentifierProperty());

    keyStoreSaveButton.disableProperty().bind(
            state.keyStoreGetFilePathProperty().isEmpty().or(
                    state.keyStoreGetPasswordProperty().isEmpty().or(
                            state.keyStoreGetIdentifierProperty().isEmpty())));
  }

  @FXML
  private void selectKeyStore() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Keystore");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setKeyStoreGetFilePath(
              file.getPath());
    }
  }

  @FXML
  private void get() {
    KeyStoreModal keyStoreModal = new KeyStoreModal();
    keyStoreModal.createKeyFileFromKeyStore(state.getKeyStoreGetFilePath(),
                                            state.getKeyStoreGetKeyStorePassword().toCharArray(),
                                            state.getKeyStoreGetIdentifier(),
                                            state.getKeyStoreGetPassword().toCharArray());
  }
}
