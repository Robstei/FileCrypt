package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.io.KeyStoreHandler;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

public class KeyStoreSaveController {
  private final State state = State.getInstance();
  @FXML
  private Label keyStoreSaveKeyStoreFilePathLabel;
  @FXML
  private PasswordField keyStoreSaveKeyStorePassword;
  @FXML
  private Label keyStoreSaveKeyFilePathLabel;
  @FXML
  private TextField keyStoreSaveKeyIdentifier;
  @FXML
  private PasswordField keyStoreSaveKeyPassword;
  @FXML
  private Button keyStoreSaveButton;

  @FXML
  private void initialize() {
    keyStoreSaveKeyStoreFilePathLabel.textProperty().bind(
            state.keyStoreSaveKeyStoreFilePathProperty());
    keyStoreSaveKeyStorePassword.textProperty().bindBidirectional(
            state.keyStoreSaveKeyStorePasswordProperty());
    keyStoreSaveKeyFilePathLabel.textProperty().bind(
            state.keyStoreSaveKeyFilePathProperty());
    keyStoreSaveKeyIdentifier.textProperty().bindBidirectional(
            state.keyStoreSaveKeyIdentifierProperty());
    keyStoreSaveKeyPassword.textProperty().bindBidirectional(
            state.keyStoreSaveKeyPasswordProperty());

    keyStoreSaveButton.disableProperty().bind(
            state.keyStoreSaveKeyStorePasswordProperty().isEmpty().or(
                    state.keyStoreSaveKeyFilePathProperty().isEmpty()).or(
                    state.keyStoreSaveKeyIdentifierProperty().isEmpty()).or(
                    state.keyStoreSaveKeyPasswordProperty().isEmpty()));
  }

  @FXML
  private void selectKeyStore() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Key Store");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setKeyStoreSaveKeyStoreFilePath(file.getPath());
    }
  }

  @FXML
  private void selectKeyToSave() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Key to Save");
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
      state.setKeyStoreSaveKeyFilePath(file.getPath());
    }
  }

  @FXML
  private void save() {
    try {
      KeyStoreHandler.saveKeyInKeyStore(state.getKeyStoreSaveKeyStoreFilePath(),
                                        state.getKeyStoreSaveKeyStorePassword().toCharArray(),
                                        state.getKeyStoreSaveKeyFilePath(),
                                        state.getKeyStoreSaveKeyIdentifier(),
                                        state.getKeyStoreSaveKeyPassword().toCharArray());
    } catch (KeyStoreException | NoSuchProviderException | IOException |
             CertificateException | NoSuchAlgorithmException |
             ParserConfigurationException | SAXException e) {
      throw new RuntimeException(e);
    }
  }
}
