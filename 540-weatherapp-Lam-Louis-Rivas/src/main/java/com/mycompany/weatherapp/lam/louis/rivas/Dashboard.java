package com.mycompany.weatherapp.lam.louis.rivas;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.Tile.TextSize;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import com.mycompany.weatherapp.lam.louis.rivas.Notification;
import java.util.List;

/**
 *
 * @author Daniel Lam, Rodrigo Rivas, and Nael Louis
 */
public class Dashboard extends HBox {
    //Flag to monitor the threads
    private static boolean running = true;
    //EXECUTABLE_PATH for real Joystick output
    private final String EXECUTABLE_PATH = "src/main/C++/DHT11";
    private String choiceValue = "Current Weather";
    private TextArea tempHumidity;
    private String city;
    TextField cityField;
    private Notification notify = new Notification();
    ReadJson rj = new ReadJson();
    
    public Dashboard() throws IOException {
        this.initiateProcess();
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
        cityField = new TextField();
        
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
            if (cityField.getText().equals("")) {
                notify.ErrorDialog("City field is empty, enter a city name.");
            }
            else {
                getCity();
                //TO DO:
            }
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
        
        /*TextArea tile for temperature and humidity */
            
        tempHumidity = new TextArea();
        tempHumidity.setEditable(false);
        tempHumidity.setWrapText(true);
        
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
    /*
     * This method sanatizes the input from the user in order to look
       for the city.
    */
    public void getCity() {
        String toSanitize = cityField.getText();
        toSanitize = toSanitize.toLowerCase();
        
        toSanitize = Normalizer.normalize(toSanitize, Form.NFKC);
        Pattern patternObj = Pattern.compile("[<>]");
        Matcher matcherObj = patternObj.matcher(toSanitize);
        if (matcherObj.find()) {
            //TO DO: Alert
            notify.ErrorDialog("Invalid character(s) has been found, only use letters for the city name");
            System.out.println("Invalid input");
            cityField.setText("");
        }
        else if (Pattern.matches("[a-zA-Z0-9]", toSanitize)) {
            city = toSanitize;
        }
    }
    private void initiateProcess() throws IOException {
        ProcessBuilderClass processBuilderObj = new ProcessBuilderClass(EXECUTABLE_PATH);
        Process processObj = processBuilderObj.startProcess();   
        startThread(processObj);
    }
    private void startThread(Process processObj) {
        Thread threadObj = new Thread(() -> {
            try ( var reader = new BufferedReader(new InputStreamReader(processObj.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    runThread(line);
                    Thread.sleep(1000);
                }
            }
            catch (InterruptedException e) {
                System.err.println("Thread got interrupted!");
            }
            catch (IOException e) {
                System.err.println("IOexception!");
            }
        });
        threadObj.setDaemon(true); //Set as Daemon so on exit, it kills the thread
        threadObj.start(); //Start Thread
    }
    private void runThread(String line) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tempHumidity.appendText(line + "\n");
            }
        });
    }
    
    
}
