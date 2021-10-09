
package com.mycompany.weatherapp.lam.louis.rivas;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Daniel Lam
 */
public class MultipleCityNames extends HBox {
    private City[] cityArr;
    private City selectedCity;
    
    public MultipleCityNames(City[] cityArr) {
        this.cityArr = cityArr;
        this.buildScreen();
    }
    
    private void buildScreen() {
        VBox citiesVBox = new VBox();
        HBox buttons = new HBox();
        
        Label citiesLabel = new Label("Please choose which city you desire: ");
        ChoiceBox citiesChoiceBox = new ChoiceBox();
        for (City cityObj in cityArr) {
            citiesChoiceBox.getItems().add(cityObj);
        }
        
        
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setOnAction((event) -> {
            this.selectedCity = citiesChoiceBox.getValue();
            returnToDashboard();
        });
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction((event) -> {
            //var scene = new Scene(currentDashboard, 900, 600);
            returnToDashboard();
        });
        
        buttons.getChildren().addAll(confirmBtn, backBtn);
        citiesVBox.getChildren().addAll(citiesLabel, citiesChoiceBox, buttons);
        
        var choiceTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(Tile.TextSize.BIGGER)
                .graphic(citiesVBox)
                .build();
        
        this.getChildren().addAll(choiceTile);
    }
    private void returnToDashboard() {
        App.theStage.setScene(App.getDashboardScene());
    }
    private City[] getCityArr() {
        return cityArr;
    }
    private City getSelectedCity() {
        return selectedCity;
    }
}
