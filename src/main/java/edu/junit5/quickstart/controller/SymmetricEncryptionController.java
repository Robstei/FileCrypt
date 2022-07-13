package edu.junit5.quickstart.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class SymmetricEncryptionController {
    @FXML
    private ToggleGroup encryption_function;
    @FXML
    private VBox encryption_function_container;

    @FXML
    private void initialize() {

        encryption_function.selectedToggleProperty().addListener((observableValue, oldToggle, newToggle) -> {

            Pair[] userDataFileNamePairs = {
                    new Pair<>("decrypt", "../SymmetricEncryptionDecrypt.fxml"),
                    new Pair<>("encrypt", "../SymmetricEncryptionEncrypt.fxml")
            };

            ControllerUtil.bindViewsToToggleOptions(newToggle, encryption_function_container, 2, userDataFileNamePairs);

        });
    }
}
