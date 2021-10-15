/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.mycompany.weatherapp.lam.louis.rivas.HTTPURLConnection;
import static org.junit.Assert.*;

/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class ReadJsonTest {
    
    public ReadJsonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of readCurrentAPI method, of class ReadJson.
     */
    @Test
    public void testReadCurrentAPI() throws Exception {
        System.out.println("readCurrentAPI");
        String jsonString = "";
        ReadJson instance = new ReadJson();
        Weather expResult = null;
        Weather result = instance.readCurrentAPI(jsonString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read7DaysAPI method, of class ReadJson.
     */
    @Test
    public void testRead7DaysAPI() throws Exception {
        System.out.println("read7DaysAPI");
        //HTTPURLConnection newCon = HTTPURLConnection(10.0,10.0);
        String jsonString = "";
        ReadJson instance = new ReadJson();
        List<Weather> expResult = new ArrayList<>(7);
        List<Weather> result = instance.read7DaysAPI(jsonString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
