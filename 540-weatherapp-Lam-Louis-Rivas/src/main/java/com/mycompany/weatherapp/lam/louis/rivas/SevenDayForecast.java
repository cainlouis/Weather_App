/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * SevenDayForecast class contains all the code to display the GUI of the 7 day forecast
 * @author Daniel Lam
 */
public class SevenDayForecast extends VBox {
    private List<Weather> weatherList;
    
    /**
     * Parameterized Constructor for SevenDayForecast
     * @param weatherList
     * @throws IOException 
     */
    public SevenDayForecast(List<Weather> weatherList) throws IOException {
        this.weatherList = weatherList;
        this.buildScreen();
    }
    /**
     * buildScreen() sets up all the JavaFX containers and add all of its respective content into it
     */
    private void buildScreen() {
        LocalDate localDate;
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm:ss a zz");
        
        //Loop through all 7 day forecast Weather objects
        ArrayList<Tile> tileArr = new ArrayList<>();
        for (int i = 0; i < weatherList.size(); i++) {
            
            //Weather tile
            Weather weatherObj = weatherList.get(i);
            
            //Image tile
            var imageTile = TileBuilder.create()
                .skinType(Tile.SkinType.IMAGE)
                .prefSize(200, 200)
                .textSize(Tile.TextSize.BIGGER)
                .image(new Image(weatherObj.getIcon()))
                .imageMask(Tile.ImageMask.ROUND)
                .text(weatherObj.getDescription())
                .textAlignment(TextAlignment.CENTER)
                .build();
            
            long unixSunrise = Long.parseLong(weatherObj.getSunrise());
            long unixSunset = Long.parseLong(weatherObj.getSunset());
            
            String weatherInfo = "Max temperature: " + weatherObj.getMaxTemp() + "°C" + "\n"
                               + "Min temperature: " + weatherObj.getMinTemp() + "°C" + "\n"
                               + "Humidity: " + weatherObj.getHumidity() + "%" + "\n"
                               + "Sunrise: " + Instant.ofEpochSecond(unixSunrise).atZone(ZoneId.of(weatherObj.getTimezone())).format(timeFormat) + "\n"
                               + "Sunset: " + Instant.ofEpochSecond(unixSunset).atZone(ZoneId.of(weatherObj.getTimezone())).format(timeFormat) + "\n"
                               + "Pressure: " + weatherObj.getPressure() + " hPa" + "\n"
                               + "UV Index: " + weatherObj.getUv() + "\n"
                               + "Wind Speed: " + weatherObj.getWindSpeed() + " km/h" + "\n"
                               + "Wind Gust: " + weatherObj.getWindGust() + " km/h";
            
            TextArea weatherField = new TextArea(weatherInfo);
            weatherField.setEditable(false);
            weatherField.setWrapText(true);
            weatherField.setPrefSize(200,300);
                    
            weatherField.setStyle("-fx-control-inner-background: #2A2A2A; "
                     + "-fx-text-inner-color: white;"
                     + "-fx-text-box-border: transparent;"
                     + "-fx-font-size: 13");
            
            VBox weatherVBox = new VBox(imageTile, weatherField);
            
            long unixTime = Long.parseLong(weatherObj.getDt());
            localDate = LocalDate.ofInstant(Instant.ofEpochSecond(unixTime), ZoneId.systemDefault());
            
            var weatherTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(230, 500)
                .textSize(Tile.TextSize.BIGGER)
                .title(localDate.format(DateTimeFormatter.ofPattern("EE"
                        + ", MMM d y")))
                .graphic(weatherVBox)
                .build();
            
            tileArr.add(weatherTile);
        }
        
        //Alert text area
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
        
        //Alert tile
        VBox alertVBox = new VBox(alertTextArea);
        
        var alertTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(814, 300)
                .textSize(Tile.TextSize.BIGGER)
                .title("Alerts")
                .graphic(alertVBox)
                .build();
        
        
        //Exit button
        Button exit = new Button("Return to Dashboard");
        exit.setOnAction((event) -> {
            App.theStage.setScene(App.getDashboardScene());
        });
        
        //Exit tile
        var exitTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .prefSize(814, 300)
                .textSize(Tile.TextSize.BIGGER)
                .title("Return")
                .graphic(exit)
                .build();
        
        //Layout all of the tiles together
        HBox weatherHbox = new HBox();
        ArrayList<VBox> columns = new ArrayList<>();
        VBox column = new VBox();
        for (int i = 0; i < tileArr.size(); i++) {
            column.getChildren().add(tileArr.get(i));
            columns.add(column);
            column = new VBox();
            column.setSpacing(3);
        }
        weatherHbox.getChildren().addAll(columns);
        weatherHbox.setSpacing(3);
                
        HBox alertAndExitHbox = new HBox();
        alertAndExitHbox.getChildren().addAll(alertTile, exitTile);
        alertAndExitHbox.setSpacing(3);
        
        //Assign entire layout to SevenDayForecast object
        this.getChildren().addAll(weatherHbox, alertAndExitHbox);
        this.setSpacing(3);
    }
}
