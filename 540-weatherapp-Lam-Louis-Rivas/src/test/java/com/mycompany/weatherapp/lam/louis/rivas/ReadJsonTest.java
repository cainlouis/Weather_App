/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import java.io.IOException;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class ReadJsonTest {
    
    HTTPURLConnection newCon;
    
    public ReadJsonTest() {
    }
    
    @BeforeClass
    void setUpClass() {
        this.newCon = new HTTPURLConnection();
    }
    

    /**
     * Test of readCurrentAPI method, of class ReadJson.
     * This method fetches for the current day forecast and returns a weather
     * object containing all the information.
     */
    @Test
    public void testReadCurrentAPI() throws Exception {
        System.out.println("readCurrentAPI");
        String jsonString = newCon.sendRequest(45.50, 73.56);
        ReadJson instance = new ReadJson();
        Weather result = instance.readCurrentAPI(jsonString);
        assertNotNull(result);
    }

    /**
     * Test of read7DaysAPI method, of class ReadJson.
     * This method fetches for 7 days forecast and returns a list
     * containing all the information.
     */
    @Test
    public void testRead7DaysAPI() throws Exception {
        System.out.println("read7DaysAPI");
        String jsonString = newCon.sendRequest(45.50, 73.56);
        ReadJson instance = new ReadJson();
        List<Weather> result = instance.read7DaysAPI(jsonString);
        assertNotNull(result);

    }


    /**
     * Test of searchCities method, of class ReadJson.
     * This method search for the city within all the possible cities
     * returning a list of those cities that match with the input city.
     */
    @Test
    public void testSearchCities() throws IOException {
        System.out.println("searchCities");
        String city = "longueuil";
        ReadJson instance = new ReadJson();
        
        List<City> result = instance.searchCities(city);
        assertEquals("Longueuil", result.get(0).getName());

    }
    
}
