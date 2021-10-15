package com.mycompany.weatherapp.lam.louis.rivas;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
/**
 *
 * @author Nael Louis 
 */
public class Notification {
    /**
     * Create an warning dialog and display the string it gets as an input
     * @param message contains the cause of the error an the action the user 
     * should follow 
     */
    public void warningDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Warning!");
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    /**
     * Create an warning dialog and display the string it gets as an input
     * @param message contains the cause of the error an the action the user 
     * should follow 
     */
    public void informationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("For your information!");
        alert.setContentText(message);

        alert.showAndWait();
    }
}