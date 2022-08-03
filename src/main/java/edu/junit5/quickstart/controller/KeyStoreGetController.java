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
 * The type Key store get controller.
 *
 * @author Robin Steil
 */
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

  /**
   * Initialize.
   */
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
    ControllerUtil.setPropertyToFilePath(
            state.keyStoreGetKeyStoreFilePathProperty(),
            "Select Keystore",
            new Pair<>("key store (.keystore)",
                       "*.keystore"));
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
      ControllerUtil.showModal(new OperationResult(true,
                                                   "Successfully got key with" +
                                                           " identifier " + state.getKeyStoreGetKeyIdentifier() + " from key store"));
    } catch (Exception e) {
      ControllerUtil.showModal(new OperationResult(false, e.getMessage(), e));
    }
  }
}
