package edu.junit5.quickstart.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * The Signature controller.
 *
 * @author Robin Steil
 */
public class SignatureController {

  @FXML
  private ToggleGroup signature_direction;

  @FXML
  private VBox signature_function_container;

  @FXML
  private void initialize() {
    List<Pair<String, String>> userDataFileNamePairs = new ArrayList<>(
            List.of(
                    new Pair<>("sign", "../SignatureSign.fxml"),
                    new Pair<>("verify", "../SignatureVerify.fxml")));
    ControllerUtil.bindViewsToToggleOptions(signature_direction,
                                            signature_function_container, 2,
                                            userDataFileNamePairs);
  }
}
