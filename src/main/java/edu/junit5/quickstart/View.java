package edu.junit5.quickstart;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View {

    Controller controller;
    Model model;
    HBox root;

    public View(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;


        //String javaVersion = System.getProperty("java.version");
        //String javafxVersion = System.getProperty("javafx.version");

        root = new HBox();
        TextField textField = new TextField();
        Button button = new Button("Submit");
        Label label = new Label("label Text");
        button.addEventHandler(ActionEvent.ACTION, e -> {
            controller.test();
        });
        SimpleStringProperty simpleStringProperty =
                new SimpleStringProperty(textField.getText());
        label.textProperty().bind(textField.textProperty());


//        StringBinding stringBinding = new StringBinding() {
//
//            {
//                super.bind(label.textProperty());
//            }
//
//            @Override
//            protected String computeValue() {
//                ObservableList dependendies = super.getDependencies();
//                Observable dependendie = dependendies.get(0);
//                return;
//            }
//
//        };


        root.getChildren().add(textField);
        root.getChildren().add(button);
        root.getChildren().add(label);
        //Label l = new Label("Hello, JavaFX " + javafxVersion + ", running
        // on " +
        //        "Java " + javaVersion + ".");
        //Scene scene = new Scene(new StackPane(l), 640, 480);


    }

    public HBox getRoot() {
        return root;
    }
}