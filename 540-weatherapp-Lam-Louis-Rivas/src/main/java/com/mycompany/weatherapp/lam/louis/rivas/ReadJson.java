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
    public ReadJson(String jsonString) throws JsonProcessingException {
        this.jsonTree = mapper.readTree(jsonString);
    }
    
   
    public List readCities() throws FileNotFoundException, IOException {
        
        
        String jsonPath = "src/main/Json/city.list.min.json";
        List <City> cities = Arrays.asList(mapper.readValue(Paths.get(jsonPath).toFile(), City[].class));
        return cities;
    }
    
    public static Weather readCurrentAPI(){
        Weather weather = new Weather();
          
        weather.setTemp(jsonTree.at("/current/temp").asText()); // Setting the temperature
        weather.setHumidity(jsonTree.at("/current/humidity").asText());
        weather.setDescription(jsonTree.at("/current/weather/0/description").asText());
        weather.setIcon(jsonTree.at("/current/weather/0/icon").asText());
        return weather;
        
    }
    
    public static List<Weather> read7DaysAPI(){
        List<Weather> sevenDays = new ArrayList<Weather>();
        Weather newWeather;
        for(int i = 1; i<8; i++){
            newWeather = new Weather();
            newWeather.setTempday(jsonTree.at("/daily/"+i+"/temp/day").asText());
            newWeather.setTempNight(jsonTree.at("/daily/"+i+"/temp/night").asText());
            newWeather.setTempEvening(jsonTree.at("/daily/"+i+"/temp/eve").asText());
            newWeather.setTempMorning(jsonTree.at("/daily/"+i+"/temp/morn").asText());
            newWeather.setHumidity(jsonTree.at("/daily/"+i+"/humidity").asText());
            newWeather.setDescription(jsonTree.at("/daily/"+i+"/weather/0/description").asText());
            newWeather.setIcon(jsonTree.at("/daily/"+i+"/weather/0/icon").asText());
            sevenDays.add(newWeather);
        }
        
        
        return sevenDays;
    }
       
    
}
