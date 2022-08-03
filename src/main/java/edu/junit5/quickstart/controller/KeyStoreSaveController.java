package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.OperationResult;
import edu.junit5.quickstart.io.KeyStoreHandler;
import edu.junit5.quickstart.state.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Pair;

/**
 * The type Key store save controller.
 *
 * @author Robin Steil
 */
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
    ControllerUtil.setPropertyToFilePath(
            state.keyStoreSaveKeyStoreFilePathProperty(),
            "Select Key Store",
            new Pair<>("key store (.keystore)",
                       "*.keystore"));
  }

  @FXML
  private void selectKeyToSave() {
    ControllerUtil.setPropertyToFilePath(
            state.keyStoreSaveKeyFilePathProperty(),
            "Select Key to Save",
            new Pair<>("key file",
                       "*"));
  }

  @FXML
  private void save() {
    try {
      KeyStoreHandler.saveKeyInKeyStore(state.getKeyStoreSaveKeyStoreFilePath(),
                                        state.getKeyStoreSaveKeyStorePassword().toCharArray(),
                                        state.getKeyStoreSaveKeyFilePath(),
                                        state.getKeyStoreSaveKeyIdentifier(),
                                        state.getKeyStoreSaveKeyPassword().toCharArray());
      ControllerUtil.showModal(new OperationResult(true,
                                                   "successfully saved the " +
                                                           "key from " + state.getKeyStoreSaveKeyFilePath() + " in key store"));
    } catch (Exception e) {
      ControllerUtil.showModal(new OperationResult(false, e.getMessage(), e));
    }
  }
}
