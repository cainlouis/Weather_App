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
    private static InputValidation inputVal;
    public ReadJson() throws IOException {
        this.cities = readCities();
        this.inputVal = new InputValidation();
    }
    
   /**
     * Create a list of available cities using the json provided by weatherapi
     * @return List of cities
     * should follow 
     */
    private List readCities() throws FileNotFoundException, IOException {
        String jsonPath = "src/main/Json/city.list.min.json";
        List <City> cities = Arrays.asList(mapper.readValue(Paths.get(jsonPath).toFile(), City[].class));
        return cities;
    }
    
    /**
     * Create a weather object using the information fetched from the api
     * @param jsonString contains the response from the weatherapi 
     * @return Weather object with all the information.
     * should follow 
     */
    
    public Weather readCurrentAPI(String jsonString) throws JsonProcessingException{
        jsonTree = mapper.readTree(jsonString);
        Weather weather = new Weather();
        
        weather.setTimezone(jsonTree.at("/timezone").asText());
        weather.setTemp(inputVal.validateString(jsonTree.at("/current/temp").asText())); // Setting the temperature
        weather.setFeelsLike(inputVal.validateString(jsonTree.at("/current/feels_like").asText()));
        weather.setHumidity(inputVal.validateString(jsonTree.at("/current/humidity").asText()));
        weather.setDescription(inputVal.validateString(jsonTree.at("/current/weather/0/description").asText()));
        weather.setSunrise(inputVal.validateString(jsonTree.at("/current/sunrise").asText()));
        weather.setSunset(inputVal.validateString(jsonTree.at("/current/sunset").asText()));
        weather.setPressure(inputVal.validateString(jsonTree.at("/current/pressure").asText()));
        weather.setUv(inputVal.validateString(jsonTree.at("/current/uvi").asText()));
        weather.setVisibility(inputVal.validateString(jsonTree.at("/current/visibility").asText()));
        weather.setWindSpeed(inputVal.validateString(jsonTree.at("/current/wind_speed").asText()));
        weather.setWindGust(inputVal.validateString(jsonTree.at("/current/wind_gust").asText()));
        weather.setIcon(jsonTree.at("/current/weather/0/icon").asText());
        weather.setAlertDesc(inputVal.validateString(jsonTree.at("/alerts/description/").asText()));
        weather.setAlertEvent(inputVal.validateString(jsonTree.at("/alerts/event/").asText()));
        return weather;
        
    }
    
    /**
     * Create a List of weather objects using the information fetched from the api for 7 days
     * @param jsonString contains the response from the weatherapi 
     * @return List of Weather objects with all the information.
     * should follow 
     */
    
    public List<Weather> read7DaysAPI(String jsonString) throws JsonProcessingException{
        jsonTree = mapper.readTree(jsonString);
        List<Weather> sevenDays = new ArrayList<Weather>();
        Weather newWeather;
        for(int i = 0; i<7; i++){
            newWeather = new Weather();
            newWeather.setTimezone(jsonTree.at("/timezone").asText());
            newWeather.setMaxTemp(inputVal.validateString(jsonTree.at("/daily/"+i+"/temp/max").asText()));
            newWeather.setMinTemp(inputVal.validateString(jsonTree.at("/daily/"+i+"/temp/min").asText()));
            newWeather.setHumidity(inputVal.validateString(jsonTree.at("/daily/"+i+"/humidity").asText()));
            newWeather.setSunrise(inputVal.validateString(jsonTree.at("/daily/"+i+"/sunrise").asText()));
            newWeather.setSunset(inputVal.validateString(jsonTree.at("/daily/"+i+"/sunset").asText()));
            newWeather.setPressure(inputVal.validateString(jsonTree.at("/daily/"+i+"/pressure").asText()));
            newWeather.setUv(inputVal.validateString(jsonTree.at("/daily/"+i+"/uvi").asText()));
            newWeather.setWindSpeed(inputVal.validateString(jsonTree.at("/daily/"+i+"/wind_speed").asText()));
            newWeather.setWindGust(inputVal.validateString(jsonTree.at("/daily/"+i+"/wind_gust").asText()));
            newWeather.setDescription(inputVal.validateString(jsonTree.at("/daily/"+i+"/weather/0/description").asText()));
            newWeather.setIcon(inputVal.validateString(jsonTree.at("/daily/"+i+"/weather/0/icon").asText()));
            newWeather.setAlertDesc(inputVal.validateString(jsonTree.at("/alerts/description/").asText()));
            newWeather.setAlertEvent(inputVal.validateString(jsonTree.at("/alerts/event/").asText()));
            newWeather.setDt(jsonTree.at("/daily/"+i+"/dt").asText());
            sevenDays.add(newWeather);
        }
        
        
        return sevenDays;
    }
    
    /**
     * Returns the cities variable containing all the available cities.
     * @return List of cities.
     * should follow 
     */

    public List<City> getCities() {
        return cities;
    }
    
    
    /**
     * Create a weather object using the information fetched from the api
     * @param jsonString contains the response from the weatherapi 
     * @return Weather object with all the information.
     * should follow 
     */
    public List<City> searchCities(String city){
        List<City> newCities = new ArrayList<>();
        for (City c : cities){
            if(c.getName().equalsIgnoreCase(city)){
                newCities.add(c);
            }
        }
        
        return newCities;  
    }
    
}
