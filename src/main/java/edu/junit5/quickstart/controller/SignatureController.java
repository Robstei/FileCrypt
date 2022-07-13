package edu.junit5.quickstart.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class SignatureController {

    @FXML
    private ToggleGroup signature_direction;

    @FXML
    private VBox signature_function_container;

    @FXML
    private void initialize() {
        signature_direction.selectedToggleProperty().addListener((observableValue, oldToggle, newToggle) -> {

            Pair[] userDataFileNamePairs = {
                    new Pair<>("sign", "../SignatureCreate.fxml"),
                    new Pair<>("validate", "../SignatureValidate.fxml")
            };

            ControllerUtil.bindViewsToToggleOptions(newToggle, signature_function_container, 2, userDataFileNamePairs);
        });
    }

}
