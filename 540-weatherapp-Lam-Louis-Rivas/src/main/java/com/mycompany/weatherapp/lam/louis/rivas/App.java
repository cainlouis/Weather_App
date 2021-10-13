package com.mycompany.weatherapp.lam.louis.rivas;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    public static Stage theStage;
    private static Scene dashboardScene;
    
    @Override
    public void start(Stage stage) throws IOException {
        var scene = new Scene(new Dashboard(), 900, 600);
        App.dashboardScene = scene;
        App.theStage = stage;
        
        //Set the active scene
        theStage.setScene(scene);
        theStage.setTitle("Weather App Dashboard");
        theStage.show();
        
        // Make sure the application quits completely on close
        theStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
    public static Scene getDashboardScene() {
        return dashboardScene;
    }

}