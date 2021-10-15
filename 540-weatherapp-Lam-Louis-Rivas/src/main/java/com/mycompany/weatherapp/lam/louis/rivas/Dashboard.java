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
import javafx.scene.Scene;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 *
 * @author Daniel Lam, Rodrigo Rivas, and Nael Louis
 **/
public class Dashboard extends HBox {
    //Flag to monitor the threads
    private boolean running = true;
    //EXECUTABLE_PATH for real Joystick output
    private final String EXECUTABLE_PATH = "src/main/C++/DHT11";
    private String choiceValue = "Current Weather";
    private TextArea tempHumidity;
    private String city;
    private TextField cityField;
    private Notification notify = new Notification();
    private ReadJson rj;
    private City selectedCity;
    private String chosenCity;
    private TextArea weatherField;
    private HTTPURLConnection httpConnection;
    private List<City> citiesFromInput;
    private ChoiceBox cityCB;
    private FlowPane cityCBFp;


    
    public Dashboard() throws IOException {
        this.initiateProcess();
        this.buildScreen();
        rj = new ReadJson();
        httpConnection = new HTTPURLConnection();
    }
    
    public void buildScreen() {
            
        //Tile for clock 
        var clockTile = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(350, 300)
                .title("Date & Time")
                .dateVisible(true)
                .locale(Locale.CANADA)
                .running(true)
                .build();
        
        //Tile for Image of Weather
        var imgTile = TileBuilder.create()
                .skinType(SkinType.IMAGE)
                .prefSize(350, 250)
                .textSize(TextSize.BIGGER)
                .image(new Image(this.getClass().getResourceAsStream("/images/blank.png")))
                .imageMask(Tile.ImageMask.ROUND)
                .text("")
                .textAlignment(TextAlignment.CENTER)
                .build();
        
        /*TextArea tile for the weather*/
        weatherField = new TextArea("You haven't chosen a city yet! Please input a city name.");
        weatherField.setEditable(false);
        weatherField.setWrapText(true);
        weatherField.setStyle("-fx-control-inner-background: #2A2A2A; "
                 + "-fx-text-inner-color: white;"
                 + "-fx-text-box-border: transparent;");
        
        VBox weatherFieldBox = new VBox(imgTile, weatherField);
        
        //Tile for Weather
        var weatherTile = TileBuilder.create()
                .skinType(SkinType.CUSTOM)
                .prefSize(350, 300)
                .title("Current weather")
                .graphic(weatherFieldBox)
                .build();
        
        /*Tile for choiceBox */
                
        //ChoiceBox and it's values
        ChoiceBox choiceBox = new ChoiceBox();
        
        //Set the values of the choiceBox
        choiceBox.getItems().add(choiceValue);
        choiceBox.getItems().add("7 Days Forecast");
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
        
        //ChoiceBox for the cities
        cityCB = new ChoiceBox();
        cityCB.setPrefWidth(200);
        
        //Leaving cityCB to be populated on update
        
        //set on action
        cityCB.setOnAction((event) -> {
            if (!cityCB.getItems().isEmpty()) {
                chosenCity = cityCB.getValue().toString();
            }
        });
        //Label for cityCB
        Label cityCBLabel = new Label("Choose the city you want: ");
        cityCBLabel.setTextFill(Color.WHITE);
        
        //Hbox for the choiceBox
        HBox cbBox = new HBox(choiceBox);
        FlowPane cbFp = new FlowPane(options, cbBox);
        
        //Hbox for the textfield
        HBox txtBox = new HBox(cityField);
        FlowPane txtFp = new FlowPane(cityLabel, txtBox);
        
        //Hbox for the cityCB 
        HBox cityCBBox = new HBox(cityCB);
        cityCBFp = new FlowPane(cityCBLabel, cityCB);
        cityCBFp.setVisible(false);
        
        VBox vb = new VBox(cbFp, txtFp, cityCBFp);
        
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
                cityCBFp.setVisible(false);
                notify.warningDialog("City field is empty, enter a city name.");
            }
            else {
                //Sanitize the user input
                getCity();
                //Clear choiceBox for cities so it disappear when there's only one city or when the user input changes
                clearChoiceBox();
                
                //get the cities that have the same name
                citiesFromInput = rj.searchCities(city);
                int size = citiesFromInput.size();
                //Verify that there's is a city with that name if not notify
                try {
                    selectedCity = citiesFromInput.get(0);
                }
                catch (IndexOutOfBoundsException e) {
                    notify.warningDialog("City doesn't exist! Please try again.");
                }
                
                //decide what action to take according to the size
                actionOnSize(size);
                
                //if there is more than 2 cities and the user chose one of the choices or there is only 1 city
                if ((size > 1 && cityCB.getValue() != null) || size == 1) {
                    //get coordinates
                    Map<String, Double> coord = selectedCity.getCoord();
                    String json;
                    try {
                        //create the query as a string (json) and fetch weather
                        json = httpConnection.sendRequest(coord.get("lat"), coord.get("lon"));
                        Weather weather = rj.readCurrentAPI(json);
                        
                        String weatherTxt = "Temperature: " + weather.getTemp() + "°C\n"
                                +"Max/min temperature: " + weather.getMaxTemp() + "/" + weather.getMinTemp() + "°C\n"
                                +"Humidity: " + weather.getHumidity() + "%\n" + "UV index: " + weather.getUv()
                                +"\nWind gust: " + weather.getWindGust() + "km/h\nWind speed: " + weather.getWindSpeed() + "km/h\n"
                                +"Pressure: " + weather.getPressure() + "mb\n" + "Visibility: " + weather.getVisibility() +"m\n"
                                +"Sunrise: " + weather.getSunrise() + "\n" + "Sunset: " + weather.getSunset() + "\n"
                                +weather.getAlertEvent() + "\n" + weather.getAlertDesc();
                        weatherField.setText(weatherTxt);
                        Image image = new Image(weather.getIcon());
                        imgTile.setImage(image);
                        imgTile.setText(weather.getDescription());
                        if (choiceValue.equals("7 Days Forecast")) {
                            List<Weather> sevenDays= rj.read7DaysAPI(json);
                            App.theStage.setScene(new Scene(new SevenDayForecast(sevenDays)));
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
        
        HBox row2 = new HBox(weatherTile, tempHumidityTile, updateTile);
        row2.setMinWidth(350);
        row2.setSpacing(5);
        
        VBox allTiles = new VBox(row1, row2);
        allTiles.setSpacing(5);
        
        //Add rows to root
        this.getChildren().add(allTiles);
    }
    
    /**
     * Call the exit method from Platform class to close the application
     */
    private void endApplication() {
        this.running = false;
        Platform.exit();
    }
    
    /**
     * This method sanitizes the input from the user in order to look
     * for the city.
    */
    public void getCity() {
        String toSanitize = cityField.getText();
        toSanitize = toSanitize.toLowerCase();
     
        toSanitize = Normalizer.normalize(toSanitize, Form.NFKC);   
        Pattern patternObj = Pattern.compile("[<>]");
        Matcher matcherObj = patternObj.matcher(toSanitize);
        if (matcherObj.find()) {
            cityField.setText("");
            cityCBFp.setVisible(false);
            notify.warningDialog("Invalid character(s) has been found, only use letters for the city name");
        }
        else if (Pattern.matches("^[a-zA-Z]+$", toSanitize)) {
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
                System.err.println("IOException got caught!");
            }
        });
        threadObj.setDaemon(true); //Set as Daemon so on exit, it kills the thread
        threadObj.start(); //Start Thread
    }
    
    /**
     * update the tempHumidity while the application is running
     * @param line The output of the c++ code
     */
    private void runThread(String line) {
        Platform.runLater(new Runnable() { //TO-DO: test without Platform.runLater()
            @Override
            public void run() {
                tempHumidity.appendText(line + "\n");
            }
        });
    }
    
    /**
     * this method verify if the user hasn't made a choice on the many cities that have the same name
     * and set selectedCity to their choice if they did
     * @param size is the length of the list of cities with the same name
     */
    private void setSelectedCity(int size) {
        //check if the user has not already made a choice
        if (cityCB.getValue() == null) {
            for (City city : citiesFromInput) {
                //add the cities with the same name to the choicebox
                cityCB.getItems().add(city.toString());
            }
            if (!cityCB.getItems().isEmpty()){
                //set the choicebox to visible
                cityCBFp.setVisible(true);
                //prompt the user to chose from the choicebox
                notify.informationDialog("There's " + size + " with the same name!\nChoose the city you want.");
            }
        } else {
            //else prompt the user to confirm their choice and proceed 
            for (City city : citiesFromInput) {
                //if the city == chosenCity selectedCity = city
                if (city.toString().equals(chosenCity)) {
                    selectedCity = city;
                }
            }
        }
    }
    
    /**
     * clear the choice box and make it invisible on the change of input
     */
    private void clearChoiceBox() {
        if (selectedCity != null && (!selectedCity.getName().equalsIgnoreCase(city))) {
            cityCBFp.setVisible(false);
            if (!(cityCB.getItems().isEmpty())) {
                cityCB.getItems().clear();
            }
        }
    }
    
    /**
     * Check the size of the list<City> and act accordingly
     * @param size the length of the list of city
     */
    private void actionOnSize(int size) {
        //if there's only 1 city clear choicebox and set to invisible
        if (size == 1) {
            cityCBFp.setVisible(false);
            cityCB.getItems().clear();
        } else {
            //check if the user has not already made a choice and set selectedCity
            setSelectedCity(size);
        }
    }
}
