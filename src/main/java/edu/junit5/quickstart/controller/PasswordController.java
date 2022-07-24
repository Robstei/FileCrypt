package edu.junit5.quickstart.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class PasswordController {
  @FXML
  private ToggleGroup password_direction;

  @FXML
  private VBox password_function_container;

  @FXML
  private void initialize() {
    Pair[] userDataFileNamePairs = {
            new Pair<>("decrypt", "../PasswordDecrypt.fxml"),
            new Pair<>("encrypt", "../PasswordEncrypt.fxml")
    };
    ControllerUtil.bindViewsToToggleOptions(password_direction,
                                            password_function_container, 2,
                                            userDataFileNamePairs);
  }
}
