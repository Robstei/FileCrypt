package edu.junit5.quickstart.controller;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.util.HashMap;
import java.util.Map;

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
            if(newValue == null) {
                property.setValue(null);
            } else {
                property.setValue((String) newValue.getUserData());
            }
        });

        property.addListener((observableValue, oldValue, newValue) -> {
            toggleGroup.selectToggle(toggleMap.get(newValue));
        });
    }
}
