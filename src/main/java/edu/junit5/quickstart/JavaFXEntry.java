package edu.junit5.quickstart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The JavaFX entry that gets called from App.js
 *
 * @author Robin Steil
 */
public class JavaFXEntry extends Application {

  @Override
  public void start(Stage stage) {
    try {
      Parent root = FXMLLoader.load(Objects.requireNonNull(
              getClass().getResource("RootLayout.fxml"),
              "File RootLayout.fxml could not be loaded"));
      stage.setScene(new Scene(root, 800, 800));
      stage.show();
    } catch (IOException e) {
      throw new RuntimeException("could not load RootLayout.fxml", e);
    }
  }
}
