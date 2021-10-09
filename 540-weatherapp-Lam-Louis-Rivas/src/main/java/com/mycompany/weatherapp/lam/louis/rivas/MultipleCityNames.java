
package com.mycompany.weatherapp.lam.louis.rivas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Daniel Lam
 */
public class MultipleCityNames extends VBox {
    
    public MultipleCityNames() {
        this.buildScreen();
    }
    
    private void buildScreen() {
        TextField test = new TextField("test");
        Button btn = new Button("Back");
        btn.setOnAction((event) -> {
            //var scene = new Scene(currentDashboard, 900, 600);
            App.theStage.setScene(App.getDashboardScene());
        });
        this.getChildren().addAll(test, btn);
    }
}
