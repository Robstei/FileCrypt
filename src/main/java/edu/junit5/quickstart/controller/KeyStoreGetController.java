package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.io.KeyStoreHandler;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;


public class KeyStoreGetController {

  private final State state = State.getInstance();
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
            state.keyStoreGetKeyStoreFilePathProperty());
    keyStoreGetKeyStorePassword.textProperty().bindBidirectional(
            state.keyStoreGetKeyStorePasswordProperty());
    keyStoreGetKeyPassword.textProperty().bindBidirectional(
            state.keyStoreGetKeyPasswordProperty());
    keyStoreGetIdentifier.textProperty().bindBidirectional(
            state.keyStoreGetKeyIdentifierProperty());

    keyStoreSaveButton.disableProperty().bind(
            state.keyStoreGetKeyStoreFilePathProperty().isEmpty().or(
                    state.keyStoreGetKeyPasswordProperty().isEmpty().or(
                            state.keyStoreGetKeyIdentifierProperty().isEmpty())));
  }

  @FXML
  private void selectKeyStore() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Keystore");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setKeyStoreGetKeyStoreFilePath(
              file.getPath());
    }
  }

  @FXML
  private void get() {
    try {
      KeyStoreHandler.createKeyFileFromKeyStore(
              state.getKeyStoreGetKeyStoreFilePath(),
              state.getKeyStoreGetKeyStorePassword
                      ().toCharArray(),
              state.getKeyStoreGetKeyIdentifier(),
              state.getKeyStoreGetKeyPassword()
                      .toCharArray());
    } catch (KeyStoreException | NoSuchProviderException | IOException |
             CertificateException | NoSuchAlgorithmException |
             UnrecoverableKeyException | ParserConfigurationException |
             TransformerException e) {
      throw new RuntimeException(e);
    }
  }
}
