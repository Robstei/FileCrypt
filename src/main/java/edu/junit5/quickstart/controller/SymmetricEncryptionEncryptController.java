package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.Model;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import edu.junit5.quickstart.State;

import java.awt.event.MouseEvent;
import java.io.File;

public class SymmetricEncryptionEncryptController {
    public Label pathLabel;

    @FXML
    public void chooseFile(Event e){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File to Encrypt");
        System.out.println();
        File file = fileChooser.showOpenDialog(null);
        State.getState().setPath(file.getPath());
        pathLabel.setText(file.getPath());
    }

    @FXML
    public void encrypt() {
        Model.getInstance().manageEncryptWithState();
    }
}
