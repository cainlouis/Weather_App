/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

/**
 *
 * @author Daniel Lam
 */
public class SevenDayForecast extends VBox {
    private List<Weather> weatherList;
    public SevenDayForecast(List<Weather> weatherList) {
        this.weatherList = weatherList;
        this.buildScreen();
    }
    private void buildScreen() {
        ArrayList<Tile> tileArr = new ArrayList<>();
        for (int i = 0; i < weatherList.size(); i++) {          
            VBox weatherVBox = new VBox();
            
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(weatherList.get(i).getIcon()));
            
            Text descriptionText = new Text(weatherList.get(i).getDescription());
            
            Label tempLabel = new Label("Temperature: ");
            Text tempText = new Text(weatherList.get(i).getTemp());
            HBox temperatureHBox = new HBox(tempLabel, tempText);
            
            Label humidityLabel = new Label("Label: ");
            Text humidityText = new Text(weatherList.get(i).getHumidity());
            HBox humidityHBox = new HBox(humidityLabel, humidityText);
            
            weatherVBox.getChildren().addAll(imageView, descriptionText, temperatureHBox, humidityHBox);
            
            var weatherTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .skinType(Tile.SkinType.IMAGE)
                .prefSize(350, 300)
                .textSize(Tile.TextSize.BIGGER)
                .title("Day " + (i + 1))
                .graphic(weatherVBox)
                .build();
            
            tileArr.add(weatherTile);
        }
        
        Text alertEvent = new Text(weatherList.get(0).getAlertEvent());
        Text alertDesc = new Text(weatherList.get(0).getAlertDesc());
        VBox alertVBox = new VBox(alertEvent, alertDesc);
        
        var alertTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(Tile.TextSize.BIGGER)
                .graphic(alertVBox)
                .build();
        
        Button exit = new Button("Return to Dashboard");
        exit.setOnAction((event) -> {
            App.theStage.setScene(App.getDashboardScene());
        });
        
        var exitTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(Tile.TextSize.BIGGER)
                .graphic(exit)
                .build();
        
        ArrayList<HBox> rows = new ArrayList<>();
        HBox row = new HBox();
        for (int i = 0; i < tileArr.size(); i++) {
            if (i % 3 == 0) {
                rows.add(row);
                row = new HBox();
            }
            row.getChildren().add(tileArr.get(i));
        }
        row.getChildren().addAll(alertTile, exitTile);
        
        this.getChildren().addAll(rows); 
        
        //TEST EVERYTHING!
    }
}
