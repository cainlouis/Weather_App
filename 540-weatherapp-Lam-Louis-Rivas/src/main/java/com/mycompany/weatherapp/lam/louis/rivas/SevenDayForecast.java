/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Daniel Lam
 */
public class SevenDayForecast extends VBox {
    private List<Weather> weatherList;
    public SevenDayForecast(List<Weather> weatherList) throws IOException {
        this.weatherList = weatherList;
        this.buildScreen();
    }
    private void buildScreen() {
        ArrayList<Tile> tileArr = new ArrayList<>();
        for (int i = 0; i < weatherList.size(); i++) {          
            var imageTile = TileBuilder.create()
                .skinType(Tile.SkinType.IMAGE)
                .prefSize(350, 300)
                .textSize(Tile.TextSize.BIGGER)
                .image(new Image(weatherList.get(i).getIcon()))
                .imageMask(Tile.ImageMask.ROUND)
                .text(weatherList.get(i).getDescription())
                .textAlignment(TextAlignment.CENTER)
                .build();
            
            String weatherInfo = "Temperature: " + weatherList.get(i).getTemp() + "°C" + "\n"
                               + "Humidity: " + weatherList.get(i).getHumidity() + "%";
            TextArea weatherField = new TextArea(weatherInfo);
            weatherField.setEditable(false);
            weatherField.setWrapText(true);
            weatherField.setStyle("-fx-control-inner-background: #2A2A2A; "
                     + "-fx-text-inner-color: white;"
                     + "-fx-text-box-border: white;");
            
            VBox weatherVBox = new VBox(imageTile, weatherField);
            
            var weatherTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(Tile.TextSize.BIGGER)
                .title("Day " + (i + 1))
                .graphic(weatherVBox)
                .build();
            
            tileArr.add(weatherTile);
        }
        
        TextArea alertTextArea = new TextArea();
        String color = "white";
        
        String alert = "There are no alerts at the moment!";
        if (weatherList.get(0).getAlertEvent() == null && weatherList.get(0).getAlertDesc() == null) {
            alert = weatherList.get(0).getAlertEvent() + "\n" + weatherList.get(0).getAlertDesc();
            color = "#c74e30";
        }
        alertTextArea.setText(alert);
        alertTextArea.setWrapText(true);
        alertTextArea.setEditable(false);
        alertTextArea.setStyle("-fx-control-inner-background: #2A2A2A; "
                     + "-fx-text-inner-color: " + color + ";"
                     + "-fx-text-box-border: white;");
        
        VBox alertVBox = new VBox(alertTextArea);
        
        var alertTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(Tile.TextSize.BIGGER)
                .title("Alerts")
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
                .title("Return")
                .graphic(exit)
                .build();
        
        ArrayList<HBox> rows = new ArrayList<>();
        HBox row = new HBox();
        for (int i = 0; i < tileArr.size(); i++) {
            if (i % 3 == 0) {
                rows.add(row);
                row = new HBox();
                row.setSpacing(5);
            }
            row.getChildren().add(tileArr.get(i));
        }
        row.getChildren().addAll(alertTile, exitTile);
        rows.add(row);
        
        this.getChildren().addAll(rows);
        this.setSpacing(5);
    }
}
