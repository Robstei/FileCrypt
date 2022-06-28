package edu.junit5.quickstart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXEntry extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model model = Model.getInstance();
        Controller controller = new Controller(model);
        View view = new View(controller, model);
        Parent root = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));

        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }
}
