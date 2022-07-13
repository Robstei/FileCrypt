package edu.junit5.quickstart.controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Robin STeil
 */
public class ControllerUtil {

    /**
     * Binds ToggleGroup to Property. Needed since ToggleGroups can't be bound directly
     *
     * @param toggleGroup ToggleGroup that should change the value of property in the modal and should change on value
     *                    changes in the model
     * @param property    Property that represents the current UI State. Will change based on ToggleGroup change
     */
    public static void bindToggleGroupToProperty(ToggleGroup toggleGroup, StringProperty property) {

        Map<String, Toggle> toggleMap = new HashMap<>();
        for (Toggle toggle : toggleGroup.getToggles()) {
            toggleMap.put((String) toggle.getUserData(), toggle);
        }

        toggleGroup.selectToggle(toggleMap.get(property.getValue()));

        toggleGroup.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == null) {
                property.setValue(null);
            } else {
                property.setValue((String) newValue.getUserData());
            }
        });

        property.addListener((observableValue, oldValue, newValue) ->
                toggleGroup.selectToggle(toggleMap.get(newValue))
        );
    }

    public static void bindViewsToToggleOptions(Toggle toggle, Pane pane, int indexToChange, Pair<String, String>[] pairArray) {
        Node newChild = null;
        String newChildKey = (String) toggle.getUserData();
        for (Pair<String, String> pair : pairArray) {
            if (pair.getKey().equals(newChildKey)) {
                try {
                    newChild = FXMLLoader.load(Objects.requireNonNull(ControllerUtil.class.getResource(pair.getValue())));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (newChild != null) {
            pane.getChildren().set(indexToChange, newChild);
        } else {
            pane.getChildren().remove(indexToChange);
        }
    }
}
