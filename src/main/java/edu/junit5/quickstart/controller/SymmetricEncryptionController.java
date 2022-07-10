package edu.junit5.quickstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SymmetricEncryptionController {
    @FXML
    private ToggleGroup encryption_function;
    @FXML
    private VBox encryption_function_container;

    @FXML
    private void initialize() {

        encryption_function.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                encryption_function_container.getChildren().remove(encryption_function_container.getChildren().size() - 1);
                VBox newEncryptionFunctionView = null;
                try {
                    if (newToggle.getUserData().equals("decrypt")) {
                        newEncryptionFunctionView = FXMLLoader.load(getClass().getResource("../SymmetricEncryptionDecrypt.fxml"));
                    } else if (newToggle.getUserData().equals("encrypt")) {
                        newEncryptionFunctionView = FXMLLoader.load(getClass().getResource("../SymmetricEncryptionEncrypt.fxml"));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (newEncryptionFunctionView != null) {
                    encryption_function_container.getChildren().add(newEncryptionFunctionView);
                }
            }
        });
    }
}
