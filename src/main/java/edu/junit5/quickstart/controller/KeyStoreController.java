package edu.junit5.quickstart.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Key store controller.
 *
 * @author Robin Steil
 */
public class KeyStoreController {

  @FXML
  private ToggleGroup keystore_direction;

  @FXML
  private VBox keystore_function_container;

  @FXML
  private void initialize() {
    List<Pair<String, String>> userDataFileNamePairs = new ArrayList<>(
            List.of(
                    new Pair<>("save", "../KeyStoreSave.fxml"),
                    new Pair<>("get", "../KeyStoreGet.fxml"))) {
    };

    ControllerUtil.bindViewsToToggleOptions(keystore_direction,
                                            keystore_function_container, 2,
                                            userDataFileNamePairs);
  }
}
