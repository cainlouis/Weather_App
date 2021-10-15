/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class ReadJson {
    private static ObjectMapper mapper = new ObjectMapper();
    private static JsonNode jsonTree;
    private static List<City> cities;
    public ReadJson() throws IOException {
        this.cities = readCities();
    }
    
   
    private List readCities() throws FileNotFoundException, IOException {
        String jsonPath = "src/main/Json/city.list.min.json";
        List <City> cities = Arrays.asList(mapper.readValue(Paths.get(jsonPath).toFile(), City[].class));
        return cities;
    }
    
    public Weather readCurrentAPI(String jsonString) throws JsonProcessingException{
        jsonTree = mapper.readTree(jsonString);
        Weather weather = new Weather();
        
        weather.setTemp(jsonTree.at("/current/temp").asText()); // Setting the temperature
        weather.setHumidity(jsonTree.at("/current/humidity").asText());
        weather.setDescription(jsonTree.at("/current/weather/0/description").asText());
        weather.setSunrise(jsonTree.at("/current/sunrise").asText());
        weather.setSunset(jsonTree.at("/current/sunset").asText());
        weather.setPressure(jsonTree.at("/current/pressure").asText());
        weather.setUv(jsonTree.at("/current/uvi").asText());
        weather.setVisibility(jsonTree.at("/current/visibility").asText());
        weather.setWindSpeed(jsonTree.at("/current/wind_speed").asText());
        weather.setWindGust(jsonTree.at("/current/wind_gust").asText());
        weather.setIcon(jsonTree.at("/current/weather/0/icon").asText());
        weather.setAlertDesc(jsonTree.at("/alerts/description/").asText());
        weather.setAlertEvent(jsonTree.at("/alerts/event/").asText());
        return weather;
        
    }
    
    public List<Weather> read7DaysAPI(String jsonString) throws JsonProcessingException{
        jsonTree = mapper.readTree(jsonString);
        List<Weather> sevenDays = new ArrayList<Weather>();
        Weather newWeather;
        for(int i = 1; i<8; i++){
            newWeather = new Weather();
            newWeather.setMaxTemp(jsonTree.at("/daily/"+i+"/temp/max").asText());
            newWeather.setMinTemp(jsonTree.at("/daily/"+i+"/temp/min").asText());
            newWeather.setHumidity(jsonTree.at("/daily/"+i+"/humidity").asText());
            newWeather.setSunrise(jsonTree.at("/daily/"+i+"/sunrise").asText());
            newWeather.setSunset(jsonTree.at("/daily/"+i+"/sunset").asText());
            newWeather.setPressure(jsonTree.at("/daily/"+i+"/pressure").asText());
            newWeather.setUv(jsonTree.at("/daily/"+i+"/uvi").asText());
            //newWeather.setVisibility(jsonTree.at("/daily/"+i+"/uvi").asText());
            newWeather.setWindSpeed(jsonTree.at("/daily/"+i+"/wind_speed").asText());
            newWeather.setWindGust(jsonTree.at("/daily/"+i+"/wind_gust").asText());
            newWeather.setDescription(jsonTree.at("/daily/"+i+"/weather/0/description").asText());
            newWeather.setIcon(jsonTree.at("/daily/"+i+"/weather/0/icon").asText());
            newWeather.setAlertDesc(jsonTree.at("/alerts/description/").asText());
            newWeather.setAlertEvent(jsonTree.at("/alerts/event/").asText());
            sevenDays.add(newWeather);
        }
        
        
        return sevenDays;
    }

    public List<City> getCities() {
        return cities;
    }
       
    public List<City> searchCities(String city){
        List<City> newCities = new ArrayList<>();
        for(City c : cities){
            if(c.getName().equalsIgnoreCase(city)){
                newCities.add(c);
            }
        }
        
        return newCities;  
    }
    
}
