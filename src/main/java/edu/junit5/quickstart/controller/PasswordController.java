package edu.junit5.quickstart.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Password controller.
 *
 * @author Robin Steil
 */
public class PasswordController {
  @FXML
  private ToggleGroup password_direction;

  @FXML
  private VBox password_function_container;

  @FXML
  private void initialize() {
    List<Pair<String, String>> userDataFileNamePairs = new ArrayList<>(List.of(
            new Pair<>("decrypt", "../PasswordDecrypt.fxml"),
            new Pair<>("encrypt", "../PasswordEncrypt.fxml")));
    ControllerUtil.bindViewsToToggleOptions(password_direction,
                                            password_function_container, 2,
                                            userDataFileNamePairs);
  }
}
