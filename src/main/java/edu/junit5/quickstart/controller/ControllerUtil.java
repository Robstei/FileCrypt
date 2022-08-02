package edu.junit5.quickstart.controller;

import edu.junit5.quickstart.data.OperationResult;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class offers static utility methods for controllers.
 *
 * @author Robin Steil
 */
public class ControllerUtil {

  /**
   * Binds ToggleGroup to Property. Needed since ToggleGroups can't be
   * bound directly
   *
   * @param toggleGroup ToggleGroup that should change the value of
   *                    property in the state and should change on value
   *                    changes in the state
   * @param property    Property that represents the current UI State. Will
   *                    change based on ToggleGroup change
   */
  public static void bindToggleGroupToProperty(ToggleGroup toggleGroup,
                                               StringProperty property) {

    Map<String, Toggle> toggleMap = new HashMap<>();
    for (Toggle toggle : toggleGroup.getToggles()) {
      toggleMap.put((String) toggle.getUserData(), toggle);
    }

    toggleGroup.selectToggle(toggleMap.get(property.getValue()));

    toggleGroup.selectedToggleProperty().addListener(
            (observableValue, oldValue, newValue) -> {
              if (newValue == null) {
                property.setValue(null);
              } else {
                property.setValue((String) newValue.getUserData());
              }
            });

    property.addListener((observableValue, oldValue, newValue) ->
                                 toggleGroup.selectToggle(
                                         toggleMap.get(newValue))
    );
  }

  /**
   * Bind views to toggle options. The pane will change element at
   * indexToChange if the User selects a different Option from the ToggleGroup
   *
   * @param toggleGroup     the toggle group
   * @param parentContainer the parent container
   * @param indexToChange   the index to change
   * @param pairArray       the pair array that maps Strings to fxml files
   */
  public static void bindViewsToToggleOptions(ToggleGroup toggleGroup,
                                              Pane parentContainer,
                                              int indexToChange,
                                              List<Pair<String, String>> pairArray) {
    toggleGroup.selectedToggleProperty().addListener(
            (observableValue, oldToggle, newToggle) -> {
              if (newToggle == null) {
                parentContainer.getChildren().remove(indexToChange);
                return;
              }

              Node newChild = null;

              String newChildKey = (String) newToggle.getUserData();
              for (Pair<String, String> pair : pairArray) {
                if (pair.getKey().equals(newChildKey)) {
                  try {
                    newChild = FXMLLoader.load(
                            Objects.requireNonNull(
                                    ControllerUtil.class.getResource(
                                            pair.getValue()),
                                    "File " + pair.getValue() + " could not " +
                                            "be loaded"));
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                }
              }

              if (newChild != null) {
                parentContainer.getChildren().set(indexToChange, newChild);
              } else {
                parentContainer.getChildren().remove(indexToChange);
              }
            });
  }

  /**
   * Show modal with the message of the operationResult.
   *
   * @param operationResult the operation result
   */
  public static void showModal(OperationResult operationResult) {
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Result");
    VBox root = new VBox();
    Alert.AlertType alertType = operationResult.isSuccess() ?
            Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
    //Label successLabel = new Label(result);
    //Label messageLabel = new Label(operationResult.getMessage());
    //root.getChildren().addAll(successLabel, messageLabel);
    Alert alert = new Alert(alertType);
    DialogPane dialogPane = alert.getDialogPane();
    Label operationResultMessage = new Label(operationResult.getMessage());
    root.getChildren().add(operationResultMessage);

    if (operationResult.getException() != null) {

      Label exceptionDetail = new Label("Full Exception: " +
                                                operationResult.getException());
      root.getChildren().add(exceptionDetail);

      StackTraceElement[] stackTraceElements =
              operationResult.getException().getStackTrace();
      StringBuilder stringBuilder = new StringBuilder();
      for (StackTraceElement stackTraceElement : stackTraceElements) {
        stringBuilder.append(stackTraceElement.toString());
        stringBuilder.append("\n");
      }
      Label operationResultStackTraceLabel = new Label(
              stringBuilder.toString());
      ScrollPane scrollPane = new ScrollPane(operationResultStackTraceLabel);
      dialogPane.setExpandableContent(scrollPane);
    }

    dialogPane.setContent(root);
    alert.showAndWait();
    stage.setWidth(600);
  }
}
