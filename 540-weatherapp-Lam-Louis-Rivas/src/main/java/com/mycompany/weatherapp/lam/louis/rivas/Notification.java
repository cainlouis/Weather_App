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
     * Create a confirmation dialog and display the string it gets as an input
     * @param message contains the message asked to the user to confirm
     * @return return the user response
     */
    public boolean confirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation needed.");
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    
    /**
     * Create an error dialog and display the string it gets as an input
     * @param message contains the cause of the error an the action the user 
     * should follow 
     */
    public void errorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("An Error has Occured!");
        alert.setContentText(message);

        alert.showAndWait();
    }


}