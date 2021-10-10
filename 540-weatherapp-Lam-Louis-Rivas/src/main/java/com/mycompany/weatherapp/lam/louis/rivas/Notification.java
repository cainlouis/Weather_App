package com.mycompany.weatherapp.lam.louis.rivas;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
/**
 *
 * @author Nael Louis 
 */
public class Notification {
    public void ErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText(message);

        alert.showAndWait();
    }


}