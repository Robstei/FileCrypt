package edu.junit5.quickstart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXEntry extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model model = Model.getInstance();
        Controller controller = new Controller(model);
        View view = new View(controller, model);
        stage.setScene(new Scene(view.getRoot(), 800, 800));
        stage.show();
        System.out.println("in start");
    }
}
