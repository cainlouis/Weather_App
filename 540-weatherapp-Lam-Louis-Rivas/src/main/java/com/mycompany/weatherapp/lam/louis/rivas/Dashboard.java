package com.mycompany.weatherapp.lam.louis.rivas;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.TextSize;
import java.util.Locale;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Daniel Lam, Rodrigo Rivas, and Nael Louis
 */
public class Dashboard extends HBox {
    //Flag to monitor the threads
    private static boolean running = true;
    private String choiceValue = "Current Weather";
    private TextArea tempHumidity;
    
    public Dashboard() {
        this.buildScreen();
    }
    
    public void buildScreen() {
        /*Tile for the clock*/
            
        //Tile for clock 
        var clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(350, 300)
                .title("Date & Time")
                .dateVisible(true)
                .locale(Locale.CANADA)
                .running(true)
                .build();
        
        /*Tile for choiceBox */
                
        //ChoiceBox and it's values
        ChoiceBox choiceBox = new ChoiceBox();
        
        //Set the values of the choiceBox
        choiceBox.getItems().add(choiceValue);
        choiceBox.getItems().add("7 days forcast");
        //set the default value
        choiceBox.setValue(choiceValue);
        //set on action
        choiceBox.setOnAction((event) -> {
            choiceValue = choiceBox.getValue().toString();
        });
        
        //Label for the choiceBox
        Label options = new Label("Select your choice: ");
        options.setTextFill(Color.WHITE);
        
        //Textfield for the user input city
        TextField cityField = new TextField();
        
        Label cityLabel = new Label("Enter city: ");
        cityLabel.setTextFill(Color.WHITE);
        
        //Hbox for the choiceBox
        HBox cbBox = new HBox(choiceBox);
        FlowPane cbFp = new FlowPane(options, cbBox);
        
        //Hbox for the textfield
        HBox txtBox = new HBox(cityField);
        FlowPane txtFp = new FlowPane(cityLabel, txtBox);
        
        VBox vb = new VBox(cbFp, txtFp);
        
        var choiceTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .graphic(vb)
                .build();
        
                    /*Tile for update button*/
                
        //Create button and event handler
        Button update = new Button("Update");
        update.setOnAction((event) -> {
            //TO DO:
        });
        
        //Tile 
        var updateTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Update forecast")
                .graphic(update)
                .build();
        
        //Tile using a dummy image for the moment
        var imageTile = TileBuilder.create()
                .skinType(SkinType.IMAGE)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .image(new Image(this.getClass().getResourceAsStream("/images/sunny-clip-art.png")))
                .imageMask(Tile.ImageMask.ROUND)
                .text("It will be sunny today with cloudy periods")
                .textAlignment(TextAlignment.CENTER)
                .build();
        
        /*TextArea tile for X axis and timestamp */
            
        tempHumidity = new TextArea();
        tempHumidity.setEditable(false);
        
        tempHumidity.setStyle("-fx-control-inner-background: #2A2A2A; "
                 + "-fx-text-inner-color: white;"
                 + "-fx-text-box-border: transparent;");
        
        //layout of the textArea
        VBox tempHumidityBox = new VBox(tempHumidity);
        
        //Tile
        var tempHumidityTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .title("Temperature and humidity")
                .graphic(tempHumidityBox)
                .build();
        
                    /*Tile for exit button*/
                
        //Create button and event handler
        Button exit = new Button("Exit");
        exit.setOnAction((event) -> {
            endApplication();
        });
        
        //Tile 
        var exitTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .textSize(TextSize.BIGGER)
                .title("Do you want to quit?")
                .graphic(exit)
                .build();
        
        //Add tiles to Hbox
        HBox row1 = new HBox(clockTile, choiceTile, exitTile);
        row1.setMinWidth(350);
        row1.setSpacing(5);
        
        HBox row2 = new HBox(imageTile, tempHumidityTile, updateTile);
        row2.setMinWidth(350);
        row2.setSpacing(5);
        
        VBox allTiles = new VBox(row1, row2);
        allTiles.setSpacing(5);
        
        //Add rows to root
        this.getChildren().add(allTiles);
    }
    
    private void endApplication() {
        this.running = false;
        Platform.exit();
    }
}
