package edu.junit5.quickstart;

import edu.junit5.quickstart.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXEntry extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));

        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }
}
