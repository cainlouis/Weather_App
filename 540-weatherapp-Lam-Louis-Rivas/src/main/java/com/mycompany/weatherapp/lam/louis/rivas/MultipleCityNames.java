
package com.mycompany.weatherapp.lam.louis.rivas;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import java.util.Arrays;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Daniel Lam
 */
public class MultipleCityNames extends HBox {
    private City[] cityArr;
    private City selectedCity;
    private Dashboard dashboard;
    
    public MultipleCityNames(City[] cityArr, Dashboard dashboard) {
        this.dashboard = dashboard;
        this.cityArr = cityArr;
        this.buildScreen();
    }
    
    private void buildScreen() {
        VBox citiesVBox = new VBox();
        citiesVBox.setSpacing(15);
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        
        Label citiesLabel = new Label("Please choose which city you desire: ");
        citiesLabel.setTextFill(Color.WHITE);
        
        ChoiceBox<City> citiesChoiceBox = new ChoiceBox<>();
        citiesChoiceBox.getItems().addAll(Arrays.asList(cityArr));
        citiesChoiceBox.getSelectionModel().select(0);
        
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setOnAction((event) -> {
            this.selectedCity = (City) citiesChoiceBox.getSelectionModel().getSelectedItem();
            this.dashboard.setSelectedCity(selectedCity);
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
    public City[] getCityArr() {
        return cityArr;
    }
    public City getSelectedCity() {
        return selectedCity;
    }
}
