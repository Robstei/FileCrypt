package edu.junit5.quickstart.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.parallel.Resources;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SymmetricEncryptionController {
    public RadioButton encryption_function_encrypt;
    public RadioButton encryption_function_decrypt;
    public ToggleGroup encryption_function;
    public VBox encryption_function_container;


    @FXML
    public void initialize() {
        encryption_function_encrypt.setUserData("encrypt");
        encryption_function_decrypt.setUserData("decrypt");

        encryption_function.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                encryption_function_container.getChildren().remove(encryption_function_container.getChildren().size() -1);
                VBox newEncryptionFunctionView = null;
                try {
                    if(newToggle.getUserData().equals("decrypt")){
                        newEncryptionFunctionView = FXMLLoader.load(getClass().getResource("../SymmetricEncryptionDecrypt.fxml"));
                    } else if (newToggle.getUserData().equals("encrypt")) {
                        newEncryptionFunctionView = FXMLLoader.load(getClass().getResource("../SymmetricEncryptionEncrypt.fxml"));
                    }
                 } catch (IOException e) {
                throw new RuntimeException(e);
                }
                if(newEncryptionFunctionView != null) {
                    encryption_function_container.getChildren().add(newEncryptionFunctionView);
                }
            }
        });
    }
}
