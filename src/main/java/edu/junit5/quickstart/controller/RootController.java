package edu.junit5.quickstart.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class RootController {

  @FXML
  private VBox main_window;

  @FXML
  private void changeMainWindow(ActionEvent event) {
    Node targetNode = (Node) event.getTarget();
    String userData = (String) targetNode.getUserData();
    Node node;
    try {
      String newFXMLMainWindow = "";
      switch (userData) {
        case "symmetric_encryption":
          newFXMLMainWindow = "../SymmetricEncryption.fxml";
          break;
        case "password":
          newFXMLMainWindow = "../Password.fxml";
          break;
        case "signature":
          newFXMLMainWindow = "../Signature.fxml";
          break;
        case "keystore":
          newFXMLMainWindow = "../KeyStore.fxml";
          break;
      }
      node = FXMLLoader.load(
              Objects.requireNonNull(
                      getClass().getResource(newFXMLMainWindow),
                      "File " + newFXMLMainWindow + " could no be loaded"));
    } catch (IOException e) {
      throw new RuntimeException(e);

    }
    if (node != null) {
      main_window.getChildren().remove(0);
      main_window.getChildren().add(node);
    }
  }
}
