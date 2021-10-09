/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class ReadJson {
    public List readCities() throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        String jsonPath = "src/main/Json/city.list.min.json";
        List <City> cities = Arrays.asList(mapper.readValue(Paths.get(jsonPath).toFile(), City[].class));
        return cities;
    }
    
    
}
