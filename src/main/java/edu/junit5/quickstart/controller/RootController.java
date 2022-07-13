package edu.junit5.quickstart.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RootController {

    @FXML
    private VBox main_window;

    @FXML
    private void changeMainWindow(ActionEvent event) {
        Node targetNode = (Node) event.getTarget();
        String userData = (String) targetNode.getUserData();
        Node node = null;
        try {
            switch (userData) {
                case "symmetric_encryption":
                    node = FXMLLoader.load(getClass().getResource("../SymmetricEncryption.fxml"));
                    break;
                case "signature":
                    node = FXMLLoader.load(getClass().getResource("../Signature.fxml"));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        if (node != null) {
            main_window.getChildren().remove(0);
            main_window.getChildren().add(node);
        }
    }
}
